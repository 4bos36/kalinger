package studio.rashka.lib;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Kalinger;

public class Language {

    public Map<String, String> text;

    public Language() {
        text = new HashMap<String, String>();
    }

    public void loginScreen() {
        if (Kalinger.getPreference().language().equals("ru")) {
            text.put("UserIn", "Логин");
            text.put("UserCreate", "ЛОГИН");
            text.put("PasswordIn", "Пароль");
            text.put("PasswordCreate", "ПАРОЛЬ");
            text.put("RememberIn", "   Запомнить");
            text.put("Forgot", "Забыли пароль?");
            text.put("LogIn", "Войти");
            text.put("NewUser", "Создать аккаунт");
            text.put("UserCreateMessage", "Придумайте новый логин");
            text.put("Email", "E-MAIL");
            text.put("EmailCreateMessage", "Введите ваш e-mail");
            text.put("PasswordCreateMessage", "Придумайте пароль");
            text.put("ConfirmPassword", "ПОВТОРИТЕ ПАРОЛЬ");
            text.put("ConfirmPasswordCreateMessage", "Повторите пароль");
            text.put("PersonalData", "   Я соглашаюсь на обработку\n   персональных данных");
            text.put("CreateUser", "Создать аккаунт");
            text.put("Phone", "Введите номер телефона");
            text.put("PhoneCode", "СМС код");
            text.put("Confirm", "Подтвердить");
            text.put("Help", "Нужна помощь?");
        }
        else {
            text.put("UserIn", "Login");
            text.put("UserCreate", "LOGIN");
            text.put("PasswordIn", "Password");
            text.put("PasswordCreate", "PASSWORD");
            text.put("RememberIn", "   Remember");
            text.put("Forgot", "Forgot your password?");
            text.put("LogIn", "Log in");
            text.put("NewUser", "New user");
            text.put("UserCreateMessage", "Come up with a new login");
            text.put("Email", "E-MAIL");
            text.put("EmailCreateMessage", "Enter your e-mail");
            text.put("PasswordCreateMessage", "Create a password");
            text.put("ConfirmPassword", "CONFIRM PASSWORD");
            text.put("ConfirmPasswordCreateMessage", "Confirm password");
            text.put("PersonalData", "   I agree to the processing\n   of personal data");
            text.put("CreateUser", "Create an account");
            text.put("Phone", "Enter phone number");
            text.put("PhoneCode", "SMS code");
            text.put("Confirm", "Confirm");
            text.put("Help", "Help is needed?");
        }
    }

    public void dispose() {
        try {
            text.clear();
        } catch (NullPointerException e) {
            // если кто не очистился
        }
    }
}