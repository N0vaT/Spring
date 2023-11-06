# Spring

## Exercise 00 – Spring Context

Реализована слабосвязанная система, состоящая из набора компонентов (beans) и соответствующая принципам IoC/DI.
Предположим, существует интерфейс принтера, предназначенный для отображения определенного сообщения.
Этот класс имеет две реализации: PrinterWithDateTimeImpl и PrinterWithPrefixImpl. Первый класс выводит сообщения,
указывая дату/время вывода с помощью LocalDateTime, а второй класс можно использовать для установки текстового префикса
для сообщения.
В свою очередь, обе реализации принтера зависят от интерфейса Renderer, который отправляет сообщения на консоль.
Renderer также имеет две реализации: RendererStandardImpl (выводит сообщение через стандартный System.out) и
RendererErrImpl (выводит сообщения через System.err).
Renderer также зависит от интерфейса PreProcessor, который предварительно обрабатывает сообщения. Реализация
PreProcessorToUpperImpl переводит все буквы в верхний регистр, а реализация PreProcessorToLower переводит все буквы в
нижний регистр.
UML-диаграмма классов показана ниже:

![Screen_Novalib](https://github.com/N0vaT/Spring/blob/master/Diagram.png)

An example of code using these classes in a standard way:
```java
public class Main {
   public static void main(String[] args) {
       PreProcessor preProcessor = new PreProcessorToUpperImpl();
       Renderer renderer = new RendererErrImpl(preProcessor);
       PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
       printer.setPrefix("Prefix");
       printer.print("Hello!");
   }
}
```
Конфигурация Spring осуществляется через context.xml, где указаны все настройки для каждого компонента и связи между ними.
Использование этих компонентов в Spring выглядит следующим образом:
```java
public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
       Printer printer = context.getBean(“printerWithPrefix”, Printer.class);
       printer.print("Hello!");
   }
}
```

## Exercise 01 – JdbcTemplate
**Реализация:**
- Java — 1.8;
- spring-context
  postgresql
  spring-jdbc
  HikariCP

**Структура проекьа**:
- ex01
    - src
        - main
            - java
                - school21.spring.service
                    - application
                        - Main
                    - exceptions
                      - NotSavedSubEntityException
                      - UserNotFoundException
                    - models
                        - User
                        - UserMapper
                    - repositories
                        - CrudRepository
                        - UsersRepository
                        - UsersRepositoryJdbcImpl
                        - UsersRepositoryJdbcTemplateImpl

            - resources
               - context.xml
               - data.sql
               - db.properties
               - Schema.sql
    -	pom.xml

Написаны две реализации UsersRepository:
UsersRepositoryJdbcImpl (использует стандартные механизмы Statements) и UsersRepositoryJdbcTemplateImpl (основан на
JdbcTemaplte/NamedParameterJdbcTemaple). Оба класса должны принимать объект DataSource в качестве аргумента
конструктора.
В файле context.xml объявлены bean-компоненты для обоих типов репозитория с разными идентификаторами, а
также два bean-компонента типа DataSource: DriverManagerDataSource и HikariDataSource.
Кроме того, данные для подключения к БД указаны в файле db.properties и включины в context.xml с помощью ${db.url} заполнителей.
Конфигурация Spring осуществляется через context.xml, где указаны все настройки для каждого компонента и связи между ними.
Пример db.properties:
```
db.url=jdbc:postgresql://localhost:5432/postgres
db.user=postgres
db.password=postgres
db.driver.name=org.postgresql.Driver
```

В классе Main работа метода findAll продемонстрирована с использованием обоих репозиториев:
```
ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
System.out.println(usersRepository.findAll());
usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
System.out.println(usersRepository.findAll());
```

## Exercise 02 – AnnotationConfig
**Реализация:**
- Java — 1.8;
- JUnit 5 framework;
- spring-context
  postgresql
  spring-jdbc
  HikariCP
  junit-jupiter-engine
  junit-jupiter-params
  junit-jupiter-api
  mockito-core
  hsqldb

**Структура проекта**:
- ex02
    - src
        - main
            - java
                - edu.school21.service
                    - application
                        - Main
                    - config
                        - ApplicationConfig
                    - exceptions
                        - NotSavedSubEntityException
                        - UserNotFoundException
                        - UserWithEmailAlreadyExistsException
                    - models
                        - User
                        - UserMapper
                    - repositories
                        - CrudRepository
                        - UsersRepository
                        - UsersRepositoryJdbcImpl
                        - UsersRepositoryJdbcTemplateImpl
                    - services
                        - UsersService
                        - UsersServiceImpl

            - resources
                - data.sql
                - db.properties
                - Schema.sql
        - test
            - java
                - edu.school21.service
                    - config
                        - TestApplicationConfig
                    - services
                        - UsersServiceImplTest
    - pom.xml

Конфигурация Spring осуществляется через аннотации и класс конфигурации.
Реализованы пара интерфейс/классов UsersService/UsersServiceImpl с зависимостью от объявленного в ней UsersRepository.
Компоненты для UsersService и UsersRepository определены с использованием аннотаций.
В UsersServiceImpl реализован метод String SignUp(String email), который регистрирует нового пользователя и сохраняет
его данные в БД. Этот метод возвращает временный пароль, назначенный пользователю системой (эта информация
также сохранена в базе данных).
Чтобы проверить, правильно ли работает служба, написан интеграционный тест для UsersServiceImp с использованием базы
данных в памяти (HSQLDB). Конфигурация контекста для тестовой среды (DataSource для базы данных в памяти)
описана в отдельном классе TestApplicatoinConfig. Этот тест проверяет, был ли возвращен временный
пароль в методе регистрации.
