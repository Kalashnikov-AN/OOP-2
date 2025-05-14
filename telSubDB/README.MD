# Создание установщика для JavaFX приложения с использованием Inno Setup

---

## 1. Создание JAR-файла в IntelliJ IDEA

### 1.1. Настройка проекта
1. Убедитесь, что в `module-info.java` добавлены необходимые модули JavaFX:
   ```java
   requires javafx.controls;
   requires javafx.fxml;

2. Создайте новый главный класс Launcher:
   ```java
   package zabsu.telephone_sub_ui;
   import javafx.application.Application;
   // новый главный класс
   public class Launcher {
   public static void main(String[] args){
        Application.launch(TelSubApplication.class, args);
    }
   }

3. Укажите главный класс в настройках проекта:

Перейдите в File -> Project Structure -> Project Settings -> Artifacts

В разделе "Main Class" выберите ваш новый главный класс.

### 1.2. Создание Artifact
Перейдите в File -> Project Structure -> Artifacts.

Нажмите + -> JAR -> From modules with dependencies.

Выберите главный класс и укажите:

Directory for META-INF/MANIFEST.MF: src/main/resources

Включите опцию Include dependencies (если используются внешние библиотеки).

### 1.3. Сборка JAR-файла
Выберите Build -> Build Artifacts.

Нажмите Build для вашего артефакта.

Готовый JAR будет в папке out/artifacts/<Имя_проекта>_jar.

## 2. Создание установщика в Inno Setup
### 2.1. Установка Inno Setup
Скачайте Inno Setup с официального сайта.

Установите программу, следуя инструкциям мастера.

### 2.2. Написание скрипта установщика
Запустите Inno Setup и выберите Create a new script file using the Script Wizard

### 2.3. Компиляция установщика
Сохраните скрипт (.iss файл).

Нажмите Build -> Compile для создания установщика.

Готовый .exe файл будет в папке Output.



