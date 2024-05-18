# Hackaton - WalletWise
Данный проект разробатывается для мероприятия [IT_ONE CAREER HACKATHON 2024](https://www.zavodit.ru/ru/calendar/event/53).

Исполнительная команда OutEast

# О кейсе
Сам по себе кейс делится на две категории:
- BackEnd
- Ui / Ux / App

## BackEnd
Для реализации серверного приложения используется язык Java с фреймворком Spring.
### Архитектура
Для разработки была выбрана архитектура MVC, что подразумевает отсуствие запутанности во внутренних связках приложения. 

Приложение делится на несколько компонентов, каждый из которых выполняет свою задачу и имеет свою иерархию в архитектуре:

    ├── config                          # Конфиги \ commons
        ├── Kafka
        ├── OpenApi
        ├── Security
        └── JwtAuthFilter
    ├── controller                      # Контроллеры
        ├── Auth
        ├── Exception
        └── User
    ├── domain                          # Описание сущностей
        ├── dto
            ├── Response
            └── Request
        └── model
    ├── exception                       # Описания исключений
    ├── repository                      # Взаимодействие с сущностями в базе данных
    ├── service                         # Сервисы
    └── main app

В скором будущем планируется перейти на архитектуру DDD, так как такое решение способствует еще большему остутвию запутанностей, а так же обеспечивает лучшее понимание кода.

Иерархическая состовляющая:
`main ─> controller ─> service ─> Db`
Один контроллер может взаимодействовать с мнодеством сервисов, но червис не должен иметь в себе другой сервис. Сервис может работать с множеством моделей. Гланое приложение подключает в себя множество контроллеров.

## Ui / Ux / App
Для реализации нашего приложения использовались:
- Figma - для создания ui и ux состовляющей [ССЫЛКА](https://www.figma.com/design/bIbBl96hqfyU9jxZcyZt1x/%D0%BA%D1%80%D1%83%D1%82%D0%BE%D0%B9-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82?node-id=0%3A1&t=FtMkDnwYl8Sfsm1h-1)
- Kotlin - язык программирования, позволяющий писать быстрые и легкие приложения для мобильных устройств
- Jetpack Compose - Современный инструмент от компании Google для создания приложений под ОС Android на языке программирования Kotlin.
- Compose Navigation - Набор библиотек и инструментов, сопровождается руководством и обеспечивает продуманную структуру навигации внутри приложения
- Hilt - Dependency Injection библиотека для внедрения зависимостей, построенная на основе Dagger

### Архитектура мобильного приложения

    ├── animation                         # Jetpack navigation, transition animation
    ├── base                              # Interface Event<T> с методом send(event: T) для viewModel, для обратобки входящих событий
    ├── bi                                # Dependency Injection, Hilt
    ├── navigation                        # Логика навигации между окнами, Jetpack Navigation
    ├── screen                            # Экраны
    ├── ui\theme                          # Компоненты и темы 
    └── main app                          # Файл App: Application()




# Полезные ссылки:
- [Наша Figma](https://www.figma.com/design/bIbBl96hqfyU9jxZcyZt1x/%D0%BA%D1%80%D1%83%D1%82%D0%BE%D0%B9-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82?node-id=0%3A1&t=FtMkDnwYl8Sfsm1h-1)
- [IT_ONE CAREER HACKATHON 2024](https://www.zavodit.ru/ru/calendar/event/53)
