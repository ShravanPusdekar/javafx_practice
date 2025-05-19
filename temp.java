import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.*;

class User {
    private String username;
    private String password;
    private String role;
    private String email;

    public User(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
}

public class temp extends Application {
    private Stage primaryStage;
    private Map<String, User> usersDb = new HashMap<>();
    private User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        addDefaultUsers();
        showLoginUI();
    }

    private void addDefaultUsers() {
        usersDb.put("admin", new User("admin", "1234", "Administrator", "admin@example.com"));
        usersDb.put("user", new User("user", "pass", "Standard User", "user@example.com"));
    }

    private void showLoginUI() {
        Label title = new Label("Welcome! Please Login");
        title.setFont(Font.font(20));

        Label userLabel = new Label("Username:");
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter username");

        Label passLabel = new Label("Password:");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter password");

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);

        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(title, 0, 0, 2, 1);
        layout.add(userLabel, 0, 1);
        layout.add(usernameInput, 1, 1);
        layout.add(passLabel, 0, 2);
        layout.add(passwordInput, 1, 2);
        layout.add(loginButton, 1, 3);
        layout.add(message, 0, 4, 2, 1);

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText().trim();
            String password = passwordInput.getText();

            if (username.isEmpty() || password.isEmpty()) {
                message.setText("Both username and password are required.");
                return;
            }

            if (!usersDb.containsKey(username)) {
                message.setText("User does not exist.");
                return;
            }

            User user = usersDb.get(username);
            if (!user.getPassword().equals(password)) {
                message.setText("Incorrect password.");
                return;
            }

            currentUser = user;
            message.setText("");
            showDashboardUI();
        });

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDashboardUI() {
        BorderPane root = new BorderPane();

        Label header = new Label("Dashboard");
        header.setFont(Font.font(24));
        BorderPane.setMargin(header, new Insets(10));
        root.setTop(header);

        Label welcome = new Label("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        welcome.setFont(Font.font(16));
        VBox center = new VBox(welcome);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20));
        root.setCenter(center);

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setPrefWidth(180);

        Button profileBtn = new Button("Profile");
        profileBtn.setMaxWidth(Double.MAX_VALUE);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(profileBtn, logoutBtn);
        root.setLeft(sidebar);

        Label status = new Label("Logged in as: " + currentUser.getUsername() + " | Role: " + currentUser.getRole());
        status.setPadding(new Insets(5));
        status.setStyle("-fx-background-color: #f0f0f0;");
        root.setBottom(status);

        logoutBtn.setOnAction(e -> {
            currentUser = null;
            showLoginUI();
        });

        profileBtn.setOnAction(e -> showProfileUI());

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showProfileUI() {
        BorderPane root = new BorderPane();

        Label header = new Label("Profile");
        header.setFont(Font.font(24));
        BorderPane.setMargin(header, new Insets(10));
        root.setTop(header);

        GridPane form = new GridPane();
        form.setPadding(new Insets(20));
        form.setVgap(15);
        form.setHgap(15);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(currentUser.getUsername());
        usernameField.setDisable(true);

        Label roleLabel = new Label("Role:");
        TextField roleField = new TextField(currentUser.getRole());
        roleField.setDisable(true);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(currentUser.getEmail());

        Label newPassLabel = new Label("New Password:");
        PasswordField newPassField = new PasswordField();
        newPassField.setPromptText("Leave blank to keep current");

        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");

        Button saveBtn = new Button("Save");
        Button backBtn = new Button("Back to Dashboard");

        form.add(usernameLabel, 0, 0);
        form.add(usernameField, 1, 0);
        form.add(roleLabel, 0, 1);
        form.add(roleField, 1, 1);
        form.add(emailLabel, 0, 2);
        form.add(emailField, 1, 2);
        form.add(newPassLabel, 0, 3);
        form.add(newPassField, 1, 3);
        form.add(saveBtn, 1, 4);
        form.add(message, 1, 5);
        form.add(backBtn, 1, 6);

        BorderPane.setAlignment(backBtn, Pos.CENTER_RIGHT);
        BorderPane.setMargin(backBtn, new Insets(10));

        root.setCenter(form);

        saveBtn.setOnAction(e -> {
            String updatedEmail = emailField.getText().trim();
            String newPassword = newPassField.getText();

            if (updatedEmail.isEmpty()) {
                message.setText("Email cannot be empty.");
                return;
            }

            if (!updatedEmail.matches("[^@ ]+@[^@ ]+\\.[^@ ]+")) {
                message.setText("Invalid email format.");
                return;
            }

            currentUser.setEmail(updatedEmail);

            if (!newPassword.isEmpty()) {
                if (newPassword.length() < 4) {
                    message.setText("Password must be at least 4 characters.");
                    return;
                }
                currentUser.setPassword(newPassword);
            }

            usersDb.put(currentUser.getUsername(), currentUser);
            message.setStyle("-fx-text-fill: green;");
            message.setText("Profile updated successfully!");
        });

        backBtn.setOnAction(e -> showDashboardUI());

        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
