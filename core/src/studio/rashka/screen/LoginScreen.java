package studio.rashka.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Kalinger;
import studio.rashka.json.JSONAutorization;
import studio.rashka.json.JSONReg;
import studio.rashka.json.JSONsms;
import studio.rashka.lib.Game;
import studio.rashka.lib.JSON;
import studio.rashka.lib.State;

public class LoginScreen extends State {

    private static final int LOGIN = 0, EMAIL = 1;
    private int typeIN = LOGIN;

    private Texture logo;
    private Map<String, TextField> inputs;
    private Map<String, Label> text;
    private Map<String, ImageButton> buttons;
    private CheckBox rememberIn, agreement;
    private Stage stage;
    private boolean load = false;
    private float i = 0, x = 0;
    private float upMenu = 1920;
    private boolean isUp = false, isShow = false;

    public LoginScreen(Game game) {
        super(game);
        stage = new Stage();
        logo = new Texture("loading/logo.png");
        inputs = new HashMap<String, TextField>();
        text = new HashMap<String, Label>();
        buttons = new HashMap<String, ImageButton>();
        inputsText();
        loginText();
        inputCreateUser();
        createUserText();
        buttons();
        Gdx.input.setInputProcessor(stage);
    }

    private void inputsText() {
        inputs.put("Login", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.WHITE, Kalinger.getTextures().getTextureButtonSkin().getDrawable("Input"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("Text"))));
        inputs.get("Login").setSize((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("Login").setPosition(128 * Kalinger.getRatioMonitorW(), 680 * Kalinger.getRatioMonitorH());
        inputs.get("Login").getStyle().background.setLeftWidth(80 * Kalinger.getRatioMonitorW());
        inputs.get("Login").setMessageText(Kalinger.getLanguage().text.get("UserIn"));
        inputs.get("Login").setMaxLength(25);

        inputs.put("Password", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.WHITE, Kalinger.getTextures().getTextureButtonSkin().getDrawable("Input"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("Text"))));
        inputs.get("Password").setSize((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("Password").setPosition(128 * Kalinger.getRatioMonitorW(), 512 * Kalinger.getRatioMonitorH());
        inputs.get("Password").getStyle().background.setLeftWidth(80 * Kalinger.getRatioMonitorW());
        inputs.get("Password").setMessageText(Kalinger.getLanguage().text.get("PasswordIn"));
        inputs.get("Password").setPasswordMode(true);
        inputs.get("Password").setMaxLength(25);

        stage.addActor(inputs.get("Login"));
        stage.addActor(inputs.get("Password"));
    }

    private void loginText() {
        rememberIn = new CheckBox(Kalinger.getLanguage().text.get("RememberIn"), new CheckBoxStyle(Kalinger.getTextures().getTextureCheckBoxSkin().getDrawable("OFF"), Kalinger.getTextures().getTextureCheckBoxSkin().getDrawable("ON"), Kalinger.getFontTTF().getFont40(), Color.WHITE));
        text.put("Forgot", new Label(Kalinger.getLanguage().text.get("Forgot"), new LabelStyle(Kalinger.getFontTTF().getFont40(), Color.WHITE)));
        text.put("NewUser", new Label(Kalinger.getLanguage().text.get("NewUser"), new LabelStyle(Kalinger.getFontTTF().getFont48(), Color.WHITE)));
        text.put("LogIn", new Label(Kalinger.getLanguage().text.get("LogIn"), new LabelStyle(Kalinger.getFontTTF().getFont48(), Color.WHITE)));

        rememberIn.getStyle().checkboxOff.setMinWidth(64 * Kalinger.getRatioMonitorW());
        rememberIn.getStyle().checkboxOff.setMinHeight(64 * Kalinger.getRatioMonitorH());
        rememberIn.getStyle().checkboxOn.setMinWidth(64 * Kalinger.getRatioMonitorW());
        rememberIn.getStyle().checkboxOn.setMinHeight(64 * Kalinger.getRatioMonitorH());
        rememberIn.setSize(320 * Kalinger.getRatioMonitorW(), 64 * Kalinger.getRatioMonitorH());

        rememberIn.setPosition(112 * Kalinger.getRatioMonitorW(), 410 * Kalinger.getRatioMonitorH());
        text.get("Forgot").setPosition((Kalinger.WIDTH - 128) * Kalinger.getRatioMonitorW() - text.get("Forgot").getPrefWidth(), 416 * Kalinger.getRatioMonitorH());
        text.get("NewUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("NewUser").getPrefWidth() / 2, 96 * Kalinger.getRatioMonitorH());
        text.get("LogIn").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("LogIn").getPrefWidth() / 2, 264 * Kalinger.getRatioMonitorH());

        stage.addActor(rememberIn);
        stage.addActor(text.get("Forgot"));
        stage.addActor(text.get("NewUser"));
        stage.addActor(text.get("LogIn"));
    }

    private void inputCreateUser() {
        /*SelectBox selectBox = new SelectBox(new SelectBox.SelectBoxStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, null, new ScrollPane.ScrollPaneStyle(), new List.ListStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Color.BLACK, null)));
        selectBox.setItems("1", "2", "3");
        selectBox.setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 432) * Kalinger.getRatioMonitorH());*/

        inputs.put("UserCreateMessage", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("UserCreateMessage").setSize((Kalinger.WIDTH - 384) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("UserCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 552 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("UserCreateMessage").setMessageText(Kalinger.getLanguage().text.get("UserCreateMessage"));
        inputs.get("UserCreateMessage").setMaxLength(25);

        inputs.put("EmailCreateMessage", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("EmailCreateMessage").setSize((Kalinger.WIDTH - 384) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("EmailCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 758 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("EmailCreateMessage").setMessageText(Kalinger.getLanguage().text.get("EmailCreateMessage"));
        inputs.get("EmailCreateMessage").setMaxLength(25);

        inputs.put("PasswordCreateMessage", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("PasswordCreateMessage").setSize((Kalinger.WIDTH - 384) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("PasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 944 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("PasswordCreateMessage").setMessageText(Kalinger.getLanguage().text.get("PasswordCreateMessage"));
        inputs.get("PasswordCreateMessage").setMaxLength(25);

        inputs.put("ConfirmPasswordCreateMessage", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("ConfirmPasswordCreateMessage").setSize((Kalinger.WIDTH - 384) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("ConfirmPasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1130 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("ConfirmPasswordCreateMessage").setMessageText(Kalinger.getLanguage().text.get("ConfirmPasswordCreateMessage"));
        inputs.get("ConfirmPasswordCreateMessage").setMaxLength(25);

        inputs.put("Phone", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("Phone").setSize((Kalinger.WIDTH - 384) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("Phone").setPosition(192 * Kalinger.getRatioMonitorW(), (660 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("Phone").setMessageText(Kalinger.getLanguage().text.get("Phone"));
        //inputs.get("Phone").setMaxLength(6);

        inputs.put("PhoneCode", new TextField("", new TextFieldStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY, Kalinger.getTextures().getTextureButtonSkin().getDrawable("InputBlack"), null, Kalinger.getTextures().getTextureButtonSkin().getDrawable("TextBlack"))));
        inputs.get("PhoneCode").setSize(256 * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        inputs.get("PhoneCode").setPosition(192 * Kalinger.getRatioMonitorW(), (530 - upMenu) * Kalinger.getRatioMonitorH());
        inputs.get("PhoneCode").setMessageText(Kalinger.getLanguage().text.get("PhoneCode"));
        inputs.get("PhoneCode").setMaxLength(6);

//        stage.addActor(inputs.get("selectBox"));
        stage.addActor(inputs.get("UserCreateMessage"));
        stage.addActor(inputs.get("EmailCreateMessage"));
        stage.addActor(inputs.get("PasswordCreateMessage"));
        stage.addActor(inputs.get("ConfirmPasswordCreateMessage"));
        stage.addActor(inputs.get("Phone"));
        stage.addActor(inputs.get("PhoneCode"));
    }

    private void createUserText() {
        text.put("UserCreate", new Label(Kalinger.getLanguage().text.get("UserCreate"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY)));
        text.put("Email", new Label(Kalinger.getLanguage().text.get("Email"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY)));
        text.put("PasswordCreate", new Label(Kalinger.getLanguage().text.get("PasswordCreate"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY)));
        text.put("ConfirmPassword", new Label(Kalinger.getLanguage().text.get("ConfirmPassword"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.GRAY)));
        text.put("Help", new Label(Kalinger.getLanguage().text.get("Help"), new LabelStyle(Kalinger.getFontTTF().getFont48(), Color.WHITE)));
        text.put("CreateUser", new Label(Kalinger.getLanguage().text.get("CreateUser"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.WHITE)));
        text.put("Confirm", new Label(Kalinger.getLanguage().text.get("Confirm"), new LabelStyle(Kalinger.getFontTTF().getFont54(), Color.WHITE)));
        agreement = new CheckBox(Kalinger.getLanguage().text.get("PersonalData"), new CheckBoxStyle(Kalinger.getTextures().getTextureCheckBoxSkin().getDrawable("OFF"), Kalinger.getTextures().getTextureCheckBoxSkin().getDrawable("ON"), Kalinger.getFontTTF().getFont40(), Color.GRAY));

        agreement.getStyle().checkboxOff.setMinWidth(64 * Kalinger.getRatioMonitorW());
        agreement.getStyle().checkboxOff.setMinHeight(64 * Kalinger.getRatioMonitorH());
        agreement.getStyle().checkboxOn.setMinWidth(64 * Kalinger.getRatioMonitorW());
        agreement.getStyle().checkboxOn.setMinHeight(64 * Kalinger.getRatioMonitorH());
        agreement.setSize((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());

        text.get("UserCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 450 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("Email").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 646 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("PasswordCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 842 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("ConfirmPassword").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1028 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("Help").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("Help").getPrefWidth() / 2, (64 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("CreateUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("CreateUser").getPrefWidth() / 2, (322 - upMenu) * Kalinger.getRatioMonitorH());
        text.get("Confirm").setPosition((Kalinger.WIDTH / 2 + Kalinger.WIDTH / 6 - 32) * Kalinger.getRatioMonitorW() - text.get("Confirm").getPrefWidth() / 2, (560 - upMenu) * Kalinger.getRatioMonitorH());
        agreement.setPosition(80 * Kalinger.getRatioMonitorW(), (410 - upMenu) * Kalinger.getRatioMonitorH());

        stage.addActor(text.get("UserCreate"));
        stage.addActor(text.get("Email"));
        stage.addActor(text.get("PasswordCreate"));
        stage.addActor(text.get("ConfirmPassword"));
        stage.addActor(text.get("Help"));
        stage.addActor(text.get("CreateUser"));
        stage.addActor(text.get("Confirm"));
        stage.addActor(agreement);
    }

    private void buttons() {
        buttons.put("CreateUser", new ImageButton(new ImageButtonStyle(Kalinger.getTextures().getTextureButtonSkin().getDrawable("NULL"), null, null, null, null, null)));
        buttons.get("CreateUser").setSize(384 * Kalinger.getRatioMonitorW(), 64 * Kalinger.getRatioMonitorH());
        buttons.get("CreateUser").setPosition((Kalinger.WIDTH / 2 - 192) * Kalinger.getRatioMonitorW(), 96 * Kalinger.getRatioMonitorH());
        buttons.get("CreateUser").addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isUp) {
                    if (Kalinger.getPreference().loadSound() == 1)
                        Kalinger.getMusicSound().getClick().play();
                    if (Kalinger.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    isUp = true;
                }
            }
        });

        buttons.put("Close", new ImageButton(new ImageButtonStyle(Kalinger.getTextures().getTextureButtonSkin().getDrawable("NULL"), null, null, null, null, null)));
        buttons.get("Close").setSize(128 * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        buttons.get("Close").setPosition((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 256 - upMenu) * Kalinger.getRatioMonitorH());
        buttons.get("Close").addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isUp) {
                    if (Kalinger.getPreference().loadSound() == 1)
                        Kalinger.getMusicSound().getClick().play();
                    if (Kalinger.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    isUp = true;
                }
            }
        });

        buttons.put("Login", new ImageButton(new ImageButtonStyle(Kalinger.getTextures().getTextureButtonSkin().getDrawable("NULL"), null, null, null, null, null)));
        buttons.get("Login").setSize(896 * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        buttons.get("Login").setPosition((Kalinger.WIDTH / 2 - 448) * Kalinger.getRatioMonitorW(), 228 * Kalinger.getRatioMonitorH());
        buttons.get("Login").addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isUp) {
                    if (Kalinger.getPreference().loadSound() == 1) Kalinger.getMusicSound().getClick().play();
                    if (Kalinger.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    if (!inputs.get("Login").getText().isEmpty() && !inputs.get("Password").getText().isEmpty()) {
                        inputs.get("Login").setColor(Color.WHITE);
                        inputs.get("Password").setColor(Color.WHITE);

                        JSONAutorization autorization = new JSONAutorization();
                        autorization.login = inputs.get("Login").getText();
                        autorization.password = inputs.get("Password").getText();
                        JSON json = new JSON();
                        json.sendRequest(autorization, "POST", "http://kalinger.net/api/autorizatintoken/", true);
                    }
                    if (inputs.get("Login").getText().isEmpty()) inputs.get("Login").setColor(Color.RED);
                    if (inputs.get("Password").getText().isEmpty()) inputs.get("Password").setColor(Color.RED);
                }
            }
        });

        buttons.put("PhoneSMS", new ImageButton(new ImageButtonStyle(Kalinger.getTextures().getTextureButtonSkin().getDrawable("NULL"), null, null, null, null, null)));
        buttons.get("PhoneSMS").setSize((Kalinger.WIDTH / 2 - 115) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        buttons.get("PhoneSMS").setPosition((Kalinger.WIDTH / 2 - 64) * Kalinger.getRatioMonitorW(), (520 - upMenu) * Kalinger.getRatioMonitorH());
        buttons.get("PhoneSMS").addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isUp) {
                    if (Kalinger.getPreference().loadSound() == 1) Kalinger.getMusicSound().getClick().play();
                    if (Kalinger.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

                    if (!inputs.get("Phone").getText().isEmpty()) {
                        inputs.get("Phone").setColor(Color.WHITE);

                        JSONsms sms = new JSONsms();
                        sms.phone = inputs.get("Phone").getText();
                        JSON json = new JSON();
                        json.sendRequest(sms, "POST", "http://kalinger.net/api/phonecode/", false);
                    }
                    else if (inputs.get("Phone").getText().isEmpty()) {
                        inputs.get("Phone").setColor(Color.RED);
                    }
                }
            }
        });

        buttons.put("CreateUserConfirm", new ImageButton(new ImageButtonStyle(Kalinger.getTextures().getTextureButtonSkin().getDrawable("NULL"), null, null, null, null, null)));
        buttons.get("CreateUserConfirm").setSize((Kalinger.WIDTH - 320) * Kalinger.getRatioMonitorW(), 128 * Kalinger.getRatioMonitorH());
        buttons.get("CreateUserConfirm").setPosition((Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 320) / 2) * Kalinger.getRatioMonitorW(), (270 - upMenu) * Kalinger.getRatioMonitorH());
        buttons.get("CreateUserConfirm").addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (!isUp) {
                    if (Kalinger.getPreference().loadSound() == 1) Kalinger.getMusicSound().getClick().play();
                    if (Kalinger.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

                    if (!inputs.get("UserCreateMessage").getText().isEmpty() && !inputs.get("PasswordCreateMessage").getText().isEmpty() &&
                            !inputs.get("ConfirmPasswordCreateMessage").getText().isEmpty() && !inputs.get("EmailCreateMessage").getText().isEmpty() &&
                            !inputs.get("Phone").getText().isEmpty() && !inputs.get("PhoneCode").getText().isEmpty() &&
                            agreement.isChecked()) {
                        inputs.get("UserCreateMessage").setColor(Color.WHITE);
                        inputs.get("PasswordCreateMessage").setColor(Color.WHITE);
                        inputs.get("ConfirmPasswordCreateMessage").setColor(Color.WHITE);
                        inputs.get("EmailCreateMessage").setColor(Color.WHITE);
                        inputs.get("Phone").setColor(Color.WHITE);
                        inputs.get("PhoneCode").setColor(Color.WHITE);

                        JSONReg jsonReg = new JSONReg();
                        jsonReg.type_user = "0"; // надо в условие добавить
                        jsonReg.login = inputs.get("UserCreateMessage").getText();
                        jsonReg.password = inputs.get("PasswordCreateMessage").getText();
                        jsonReg.repeat_password = inputs.get("ConfirmPasswordCreateMessage").getText();
                        jsonReg.email = inputs.get("EmailCreateMessage").getText();
                        jsonReg.phone = inputs.get("Phone").getText();
                        jsonReg.code_phone = inputs.get("PhoneCode").getText();
                        if (agreement.isChecked()) jsonReg.check = "1";
                        JSON json = new JSON();
                        json.sendRequest(jsonReg, "POST", "http://kalinger.net/api/reg/", true);
                    }
                    if (inputs.get("UserCreateMessage").getText().isEmpty()) inputs.get("UserCreateMessage").setColor(Color.RED);
                    if (inputs.get("PasswordCreateMessage").getText().isEmpty()) inputs.get("PasswordCreateMessage").setColor(Color.RED);
                    if (inputs.get("ConfirmPasswordCreateMessage").getText().isEmpty()) inputs.get("ConfirmPasswordCreateMessage").setColor(Color.RED);
                    if (inputs.get("EmailCreateMessage").getText().isEmpty()) inputs.get("EmailCreateMessage").setColor(Color.RED);
                    if (inputs.get("Phone").getText().isEmpty()) inputs.get("Phone").setColor(Color.RED);
                    if (inputs.get("PhoneCode").getText().isEmpty()) inputs.get("PhoneCode").setColor(Color.RED);
                    //if (!agreement.isChecked())
                }
            }
        });

        stage.addActor(buttons.get("CreateUser"));
        stage.addActor(buttons.get("Close"));
        stage.addActor(buttons.get("Login"));
        stage.addActor(buttons.get("PhoneSMS"));
        stage.addActor(buttons.get("CreateUserConfirm"));
    }

    @Override
    public void update(float deltaTime) {
        if (Kalinger.getTextures().textures.get("Fon") == null) {
            i++;
            if (i == 50) {
                Kalinger.getTextures().loginScreen();
                i = 0;
                load = true;
            }
        }
        else load = true;
        if (load) {
            if (i < 320) i += deltaTime * 400;
            else if (i >= 320) i = 320;
        }
        if (isUp && !isShow) {
            if (x >= 0 && x < 1920) {
                x += deltaTime * 1800;
                if (x >= 1920) x = 1920;
                inputs.get("Login").setPosition(128 * Kalinger.getRatioMonitorW(), (680 + x) * Kalinger.getRatioMonitorH());
                inputs.get("Password").setPosition(128 * Kalinger.getRatioMonitorW(), (512 + x) * Kalinger.getRatioMonitorH());

                rememberIn.setPosition(112 * Kalinger.getRatioMonitorW(), (410 + x) * Kalinger.getRatioMonitorH());
                text.get("Forgot").setPosition((Kalinger.WIDTH - 128) * Kalinger.getRatioMonitorW() - text.get("Forgot").getPrefWidth(), (416 + x) * Kalinger.getRatioMonitorH());
                text.get("NewUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("NewUser").getPrefWidth() / 2, (96 + x) * Kalinger.getRatioMonitorH());
                text.get("LogIn").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("LogIn").getPrefWidth() / 2, (264 + x) * Kalinger.getRatioMonitorH());
                buttons.get("Login").setPosition((Kalinger.WIDTH / 2 - 448) * Kalinger.getRatioMonitorW(), (228 + x) * Kalinger.getRatioMonitorH());

                buttons.get("Close").setPosition((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 256 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("UserCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 552 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("EmailCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 758 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("PasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 944 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("ConfirmPasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1130 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("Phone").setPosition(192 * Kalinger.getRatioMonitorW(), (660 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("PhoneCode").setPosition(192 * Kalinger.getRatioMonitorW(), (530 - upMenu + x) * Kalinger.getRatioMonitorH());

                text.get("UserCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 450 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Email").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 646 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("PasswordCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 842 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("ConfirmPassword").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1028 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Help").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("Help").getPrefWidth() / 2, (64 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("CreateUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("CreateUser").getPrefWidth() / 2, (322 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Confirm").setPosition((Kalinger.WIDTH / 2 + Kalinger.WIDTH / 6 - 32) * Kalinger.getRatioMonitorW() - text.get("Confirm").getPrefWidth() / 2, (560 - upMenu + x) * Kalinger.getRatioMonitorH());
                agreement.setPosition(80 * Kalinger.getRatioMonitorW(), (410 - upMenu + x) * Kalinger.getRatioMonitorH());

                buttons.get("CreateUser").setPosition((Kalinger.WIDTH / 2 - 192) * Kalinger.getRatioMonitorW(), (96 + x) * Kalinger.getRatioMonitorH());
                buttons.get("CreateUserConfirm").setPosition((Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 320) / 2) * Kalinger.getRatioMonitorW(), (270 - upMenu + x) * Kalinger.getRatioMonitorH());
                buttons.get("PhoneSMS").setPosition((Kalinger.WIDTH / 2 - 64) * Kalinger.getRatioMonitorW(), (520 - upMenu + x) * Kalinger.getRatioMonitorH());
            }
            else if (x >= 1920) {
                isShow = true;
                isUp = false;
            }
        }
        else if (isUp && isShow) {
            if (x > 0 && x <= 1920) {
                x -= deltaTime * 1800;
                if (x < 0) x = 0;
                inputs.get("Login").setPosition(128 * Kalinger.getRatioMonitorW(), (680 + x) * Kalinger.getRatioMonitorH());
                inputs.get("Password").setPosition(128 * Kalinger.getRatioMonitorW(), (512 + x) * Kalinger.getRatioMonitorH());

                rememberIn.setPosition(112 * Kalinger.getRatioMonitorW(), (410 + x) * Kalinger.getRatioMonitorH());
                text.get("Forgot").setPosition((Kalinger.WIDTH - 128) * Kalinger.getRatioMonitorW() - text.get("Forgot").getPrefWidth(), (416 + x) * Kalinger.getRatioMonitorH());
                text.get("NewUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("NewUser").getPrefWidth() / 2, (96 + x) * Kalinger.getRatioMonitorH());
                text.get("LogIn").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("LogIn").getPrefWidth() / 2, (264 + x) * Kalinger.getRatioMonitorH());
                buttons.get("Login").setPosition((Kalinger.WIDTH / 2 - 448) * Kalinger.getRatioMonitorW(), (228 + x) * Kalinger.getRatioMonitorH());

                buttons.get("Close").setPosition((Kalinger.WIDTH - 256) * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 256 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("UserCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 552 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("EmailCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 758 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("PasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 944 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("ConfirmPasswordCreateMessage").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1130 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("Phone").setPosition(192 * Kalinger.getRatioMonitorW(), (660 - upMenu + x) * Kalinger.getRatioMonitorH());
                inputs.get("PhoneCode").setPosition(192 * Kalinger.getRatioMonitorW(), (530 - upMenu + x) * Kalinger.getRatioMonitorH());

                text.get("UserCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 450 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Email").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 646 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("PasswordCreate").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 842 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("ConfirmPassword").setPosition(192 * Kalinger.getRatioMonitorW(), (Kalinger.HEIGHT - 1028 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Help").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("Help").getPrefWidth() / 2, (64 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("CreateUser").setPosition(Kalinger.WIDTH / 2 * Kalinger.getRatioMonitorW() - text.get("CreateUser").getPrefWidth() / 2, (322 - upMenu + x) * Kalinger.getRatioMonitorH());
                text.get("Confirm").setPosition((Kalinger.WIDTH / 2 + Kalinger.WIDTH / 6 - 32) * Kalinger.getRatioMonitorW() - text.get("Confirm").getPrefWidth() / 2, (560 - upMenu + x) * Kalinger.getRatioMonitorH());
                agreement.setPosition(80 * Kalinger.getRatioMonitorW(), (410 - upMenu + x) * Kalinger.getRatioMonitorH());

                buttons.get("CreateUser").setPosition((Kalinger.WIDTH / 2 - 192) * Kalinger.getRatioMonitorW(), (96 + x) * Kalinger.getRatioMonitorH());
                buttons.get("CreateUserConfirm").setPosition((Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 320) / 2) * Kalinger.getRatioMonitorW(), (270 - upMenu + x) * Kalinger.getRatioMonitorH());
                buttons.get("PhoneSMS").setPosition((Kalinger.WIDTH / 2 - 64) * Kalinger.getRatioMonitorW(), (520 - upMenu + x) * Kalinger.getRatioMonitorH());
            }
            else if (x <= 0) {
                isShow = false;
                isUp = false;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (load) {
            batch.begin();
            if (i == 320) {
                batch.draw(Kalinger.getTextures().textures.get("Fon"), 0, 0);
                batch.draw(Kalinger.getTextures().textureRegion.get("LogIn"), 112, 700 + x, 96, 96);
                batch.draw(Kalinger.getTextures().textureRegion.get("Password"), 112, 540 + x, 96, 96);
                batch.draw(Kalinger.getTextures().textureRegion.get("Button"), Kalinger.WIDTH / 2 - 448, 224 + x, 896, 128);
            }
            batch.draw(logo, Kalinger.WIDTH / 2 - logo.getWidth() / 2, Kalinger.HEIGHT / 2 - 256 + i + x);

            batch.draw(Kalinger.getTextures().textureRegion.get("White"), Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 256) / 2, 256 - upMenu + x, Kalinger.WIDTH - 256, Kalinger.HEIGHT - 384);
            batch.draw(Kalinger.getTextures().textureRegion.get("White"), Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 448) / 2, 160 - upMenu + x, Kalinger.WIDTH - 448, 5);
            batch.draw(Kalinger.getTextures().textureRegion.get("Button"), Kalinger.WIDTH / 2 - (Kalinger.WIDTH - 320) / 2, 284 - upMenu + x, Kalinger.WIDTH - 320, 128);
            batch.draw(Kalinger.getTextures().textureRegion.get("Button"), Kalinger.WIDTH / 2 - 64, 520 - upMenu + x, Kalinger.WIDTH / 2 - 115, 128);
            batch.draw(Kalinger.getTextures().textureRegion.get("Close"), Kalinger.WIDTH - 228, Kalinger.HEIGHT - 228 - upMenu + x, 80, 80);
            batch.end();

            if (i == 320) {
                stage.act();
                stage.draw();
            }
        }
        else Kalinger.getLoading().render(batch);
    }

    @Override
    public void dispose() {
        try {
            logo.dispose();
            stage.dispose();
            inputs.clear();
            text.clear();
            buttons.clear();
            rememberIn.clear();
            agreement.clear();
            Kalinger.getTextures().textureDispose();
            Kalinger.getLanguage().dispose();
        } catch (NullPointerException e) {
            // защита от вылета
        }
    }
}