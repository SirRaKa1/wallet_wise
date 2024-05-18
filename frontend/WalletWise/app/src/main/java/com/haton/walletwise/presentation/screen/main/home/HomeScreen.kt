package com.haton.walletwise.presentation.screen.main.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haton.walletwise.R
import com.haton.walletwise.presentation.ui.theme.WiseCommon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    val pagerState = rememberPagerState(0) { 3 }

    val title = listOf("Дом", "Еда и напитки", "Жена")
    val amount = listOf("1600", "800", "800")
    val percent = listOf("50", "25", "25")
    val weight = listOf(50f, 25f, 25f)
    val icon = listOf(R.drawable.ic_home, R.drawable.ic_talerka, R.drawable.ic_ring)
    val color = listOf(Color(0xFF608757), Color(0xFFB8D9B1), Color(0xFFDFEB95))
    val pad = listOf(8, 12, 12)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(WiseCommon.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CalendarButton(
                date = "Май 2024",
                onNext = {},
                onBack = {},
                Modifier
                    .fillMaxWidth()
                    .padding(top = (prop * 40).dp)
            )
        }
        item {
            Wallets(
                homeViewModel = homeViewModel,
                pagerState = pagerState,
                modifier = Modifier.padding(top = (prop * 24).dp)
            )
        }
        item {
            CalculatePanel(
                onIncome = {},
                onExpenses = {}
            )
        }
        item {
            CostView(
                title = "Расходы за май",
                amount = "2400 ₽"
            )
        }
        items(3) {
            CostViewItem(
                icon = icon[it],
                title = title[it],
                percent = percent[it],
                amount = amount[it],
                color = color[it],
                modifier = Modifier.padding(
                    top = (prop * pad[it]).dp,
                    start = (prop + 20).dp,
                    end = (prop + 20).dp
                ),
                weight1 = weight[it],
                weight2 = 100f - weight[it],
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = (prop * 20).dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = (prop * 16).dp)
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Посмотреть все",
                        style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 12).sp)
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = (prop * 4).dp)
                            .size((prop * 16).dp),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        tint = WiseCommon.colors.fadedText,
                        contentDescription = null
                    )
                }
            }
        }
        item {
            Purposes()
        }
    }
}

@Composable
private fun CalendarButton(
    date: String,
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .padding(end = (prop * 12).dp)
                .size((prop * 24).dp),
            onClick = { onBack() }
        ) {
            Image(
                modifier = Modifier
                    .size((prop * 24).dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null
            )
        }
        Text(
            text = date,
            style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 20).sp)
        )
        IconButton(
            modifier = Modifier
                .padding(start = (prop * 12).dp)
                .size((prop * 24).dp),
            onClick = { onNext() }
        ) {
            Image(
                modifier = Modifier
                    .size((prop * 24).dp),
                painter = painterResource(id = R.drawable.arrow_next),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Wallets(
    homeViewModel: HomeViewModel,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = (prop * (-60)).dp,
        ) {
            if (pagerState.pageCount - 1 != it)
                WalletItem(
                    name = "Ваш общий счет",
                    amount = "16 400 ₽",
                    purposeCreation = "Создать цель",
                    onClick = {},
                    modifier = Modifier.height((prop * 145).dp)
                )
            else
                WalletItemAdd(
                    modifier = Modifier.height((prop * 145).dp),
                    onClick = {}
                )
        }
    }
}

@Composable
private fun WalletItem(
    name: String,
    amount: String,
    purposeCreation: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Card(
        modifier = modifier.padding(start = (prop * 20).dp, end = (prop * 60).dp),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = WiseCommon.colors.white,
            disabledContainerColor = WiseCommon.colors.gradientStart,
            disabledContentColor = WiseCommon.colors.gradientStart,
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape((prop * 16).dp),
                    brush = Brush.linearGradient(
                        arrayListOf(
                            WiseCommon.colors.gradientStart,
                            WiseCommon.colors.gradientEnd,
                        )
                    )
                )
                .padding((prop * 12).dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = WiseCommon.colors.white.copy(alpha = 0.6f),
                        shape = WiseCommon.shape.big
                    )
                    .padding( vertical = (prop * 4).dp, horizontal = (prop * 20).dp)
            ) {
                Text(
                    text = name,
                    style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 12).sp)
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = (prop * 20).dp, top = (prop * 12).dp),
                text = amount,
                style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 32).sp)
            )
            Text(
                modifier = Modifier
                    .padding(top = (prop * 24).dp),
                text = purposeCreation,
                style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 12).sp)
            )
        }
    }
}

@Composable
private fun WalletItemAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Card(
        modifier = modifier.padding(start = (prop * 20).dp, end = (prop * 60).dp),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = WiseCommon.colors.white,
            disabledContainerColor = WiseCommon.colors.gradientStart,
            disabledContentColor = WiseCommon.colors.gradientStart,
        ),
        border = BorderStroke(1.dp, WiseCommon.colors.gradientStart),
        shape = RoundedCornerShape((prop * 16).dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .height((prop * 145).dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape((prop * 16).dp),
                    color = WiseCommon.colors.gradientEnd
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size((prop * 64).dp),
                imageVector = Icons.Default.Add,
                tint = WiseCommon.colors.gradientStart,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CalculatePanel(
    onIncome: () -> Unit,
    onExpenses: () -> Unit,
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Row(
        modifier = Modifier
            .padding(start = (prop * 16).dp, end = (prop * 16).dp, top = (prop * 16).dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape((prop * 16).dp),
                color = WiseCommon.colors.primary
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CalculatePanelButton("+","Доходы", RoundedCornerShape(topStart = (prop * 12).dp, bottomStart = (prop * 12).dp), onIncome)
        Box(modifier = Modifier.height((prop * 60).dp).width(1.dp).background(WiseCommon.colors.white))
        CalculatePanelButton("-","Расходы", RoundedCornerShape(topEnd = (prop * 12).dp, bottomEnd = (prop * 12).dp), onExpenses)
    }
}

@Composable
private fun RowScope.CalculatePanelButton(
    icon: String,
    title: String,
    shape: Shape,
    onCLick: () -> Unit
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Card(
        modifier = Modifier.weight(1f),
        onClick = onCLick,
        shape = shape,
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding((prop * 8).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = WiseCommon.colors.white.copy(alpha = 0.5f),
                            shape = RoundedCornerShape((prop * 12).dp)
                        )
                        .size((prop * 24).dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = icon,
                        style = WiseCommon.typography.fieldText.copy(fontSize = (prop * 16).sp)
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(top = (prop * 8).dp),
                    text = title,
                    style = WiseCommon.typography.fieldText.copy(
                        fontSize = (prop * 10).sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
private fun CostView(
    title: String,
    amount: String,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Column(
        modifier = Modifier
            .padding(top = (prop * 32).dp, start = (prop * 20).dp, end = (prop * 20).dp)
    ) {
        Text(
            text = title,
            style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 20).sp)
        )
        Box(
            modifier = Modifier
                .padding(top = (prop * 16).dp, start = (prop * 40).dp, end = (prop * 40).dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.chart),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(
                text = amount,
                style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 24).sp)
            )
        }
    }
}

@Composable
private fun CostViewItem(
    @DrawableRes icon: Int,
    title: String,
    percent: String,
    amount: String,
    color: Color,
    weight1: Float,
    weight2: Float,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Row(modifier) {
        Image(
            modifier = Modifier.size((prop * 32).dp),
            painter = painterResource(id = icon),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (prop * 8).dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 14).sp)
                )
                Text(
                    text = "$amount ₽",
                    style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 14).sp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = (prop * 8).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height((prop * 6).dp)
                            .weight(weight1)
                            .background(
                                color = color,
                                shape = RoundedCornerShape(
                                    topStart = (prop * 12).dp,
                                    bottomStart = (prop * 12).dp
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .height((prop * 6).dp)
                            .weight(weight2)
                            .background(
                                color = WiseCommon.colors.black,
                                shape = RoundedCornerShape(
                                    bottomEnd = (prop * 12).dp,
                                    topEnd = (prop * 12).dp
                                )
                            )
                    )
                }
                Text(
                    text = "$percent %",
                    style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 10).sp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Purposes() {
    val context = LocalContext.current
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Column(
        modifier = Modifier
            .padding(top = (prop * 32).dp, start = (prop * 20).dp, end = (prop * 20).dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Цели",
                style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 20).sp)
            )
            Card(
                onClick = {},
                shape = RoundedCornerShape((prop * 20).dp),
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                ),
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = WiseCommon.colors.primary.copy(alpha = 0.25f),
                            shape = RoundedCornerShape((prop * 20).dp)
                        )
                        .size((prop * 32).dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size((prop * 24).dp),
                        imageVector = Icons.Default.Add,
                        tint = WiseCommon.colors.black,
                        contentDescription = null
                    )
                }
            }
        }
    }
    val pagerState = rememberPagerState(0) { 3 }
    Column(
        modifier = Modifier.padding(top = (prop * 8).dp, bottom = (prop * 32).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = (prop * (-60)).dp,
        ) {
            if (pagerState.pageCount - 1 != it)
                PurposesItem(
                    name = "Машина",
                    amount = "150 000 ₽",
                    amountEnd = "из 1 000 000 ₽",
                    onClick = {},
                    modifier = Modifier.height((prop * 120).dp)
                )
            else
                PurposesItemAdd(
                    modifier = Modifier.height((prop * 120).dp),
                    onClick = {}
                )
        }
    }
}

@Composable
private fun PurposesItem(
    name: String,
    amount: String,
    amountEnd: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Card(
        modifier = modifier.padding(start = (prop * 20).dp, end = (prop * 60).dp),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = WiseCommon.colors.white,
            disabledContainerColor = WiseCommon.colors.gradientStart,
            disabledContentColor = WiseCommon.colors.gradientStart,
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape((prop * 16).dp),
                    brush = Brush.linearGradient(
                        arrayListOf(
                            WiseCommon.colors.gradientStart,
                            WiseCommon.colors.gradientEnd,
                        )
                    )
                )
                .padding((prop * 12).dp),
        ) {
            Text(
                modifier = Modifier,
                text = name,
                style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 16).sp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = (prop * 25).dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = amount,
                    style = WiseCommon.typography.fadedText.copy(
                        fontSize = (prop * 14).sp,
                        color = WiseCommon.colors.blackText
                    )
                )
                Text(
                    modifier = Modifier,
                    text = amountEnd,
                    style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 12).sp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = (prop * 8).dp)
            ) {
                Box(
                    modifier = Modifier
                        .height((prop * 6).dp)
                        .weight(15f)
                        .background(
                            color = Color(0xFFDFEB95),
                            shape = RoundedCornerShape(
                                topStart = (prop * 12).dp,
                                bottomStart = (prop * 12).dp
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .height((prop * 6).dp)
                        .weight(85f)
                        .background(
                            color = WiseCommon.colors.black,
                            shape = RoundedCornerShape(
                                bottomEnd = (prop * 12).dp,
                                topEnd = (prop * 12).dp
                            )
                        )
                )
            }
        }
    }
}

@Composable
private fun PurposesItemAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    Card(
        modifier = modifier.padding(start = (prop * 20).dp, end = (prop * 60).dp),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = WiseCommon.colors.white,
            disabledContainerColor = WiseCommon.colors.gradientStart,
            disabledContentColor = WiseCommon.colors.gradientStart,
        ),
        border = BorderStroke(1.dp, WiseCommon.colors.gradientStart),
        shape = RoundedCornerShape((prop * 16).dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .height((prop * 120).dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape((prop * 16).dp),
                    color = WiseCommon.colors.gradientEnd
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size((prop * 64).dp),
                imageVector = Icons.Default.Add,
                tint = WiseCommon.colors.gradientStart,
                contentDescription = null
            )
        }
    }
}
