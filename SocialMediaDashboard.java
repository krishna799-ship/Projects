
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SocialMediaDashboard extends JFrame {
    JTextField userField;
    JPasswordField passField;
    DefaultListModel<String> contactListModel;
    JTextArea chatArea;
    JTextField messageInput;
    JPanel chatPanel;
    JScrollPane chatScroll;
    JButton sendBtn;
    String currentUser = "user"; // Dummy user, you can change this
    String botName; // Bot name will be set dynamically based on the user

    // User profile pictures
    ImageIcon userProfilePicture = new ImageIcon("path_to_user_picture.jpg");  // Change this path
    ImageIcon botProfilePicture = new ImageIcon("path_to_bot_picture.jpg");    // Change this path

    // Enhanced bot responses
    String[] greetings = {"Hi there!", "Hello!", "Hey! How can I assist you?", "Hi! What's up?"};
    String[] weatherResponses = {"I'm not sure about the weather, but I hope it's sunny!", "It looks like it's a nice day outside.", "I recommend checking your weather app!"};
    String[] helpResponses = {"I'm here to help! What do you need?", "Sure, how can I assist you?", "Please ask your question, and I'll try my best to answer."};
    String[] randomResponses = {
        "That's interesting! Tell me more.",
        "Hmm, let me think about that.",
        "What else would you like to know?",
        "Nice! Keep going.",
        "I don't quite understand, but tell me more."
    };

    Random random = new Random();

    // Typing indicator
    JLabel typingLabel;

    public static void main(String[] args) {
        new SocialMediaDashboard().showLoginPage();
    }

    public void showLoginPage() {
        setTitle("Login - SocialMediaDashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel logo = new JLabel("SocialMediaDashboard", SwingConstants.CENTER);
        logo.setFont(new Font("Serif", Font.BOLD, 28));
        logo.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(logo, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        userField = new JTextField();
        userField.setBorder(BorderFactory.createTitledBorder("Username"));

        passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Log In");
        loginBtn.setBackground(Color.BLUE);
        loginBtn.setForeground(Color.WHITE);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = String.valueOf(passField.getPassword());
            if (user.equals("user") && pass.equals("pass")) {
                showChatDashboard(user);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(userField);
        panel.add(passField);
        panel.add(loginBtn);
        add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
        setVisible(true);
    }

    public void showChatDashboard(String username) {
        currentUser = username;  // Set current user
        botName = currentUser;    // Set bot name to the current user
        setTitle("Dashboard - " + currentUser);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // Sidebar with contacts - using Indian names
        contactListModel = new DefaultListModel<>();
        contactListModel.addElement("Aarav");
        contactListModel.addElement("Isha");
        contactListModel.addElement("Vivaan");
        contactListModel.addElement("Diya");
        contactListModel.addElement("Arjun");
        contactListModel.addElement("Ananya");

        JList<String> contactList = new JList<>(contactListModel);
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        contactList.setBorder(BorderFactory.createTitledBorder("Chats"));

        JScrollPane contactScroll = new JScrollPane(contactList);
        contactScroll.setPreferredSize(new Dimension(200, 0));
        add(contactScroll, BorderLayout.WEST);

        // Chat display panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatScroll = new JScrollPane(chatArea);

        // Typing label
        typingLabel = new JLabel();
        typingLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        typingLabel.setForeground(Color.GRAY);

        // Message input panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        sendBtn = new JButton("Send");

        sendBtn.addActionListener(e -> sendMessage());
        messageInput.addActionListener(e -> sendMessage());

        messagePanel.add(messageInput, BorderLayout.CENTER);
        messagePanel.add(sendBtn, BorderLayout.EAST);

        add(chatScroll, BorderLayout.CENTER);
        add(typingLabel, BorderLayout.SOUTH);
        add(messagePanel, BorderLayout.SOUTH);

        // Handle user selection (contact selection)
        contactList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = contactList.getSelectedValue();
                chatArea.setText("Chat with " + selectedUser + ":\n");
            }
        });

        revalidate();
        repaint();
        setVisible(true);
    }

    public void sendMessage() {
        String message = messageInput.getText().trim();
        if (!message.isEmpty()) {
            chatArea.append(currentUser + ": " + message + " " + getTimeStamp() + "\n");

            // Display typing indicator
            typingLabel.setText(botName + " is typing...");

            // Simulate chatbot response after a slight delay
            javax.swing.Timer timer = new javax.swing.Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    typingLabel.setText("");
                    String botMessage = getBotResponse(message);
                    chatArea.append(botName + ": " + botMessage + " " + getTimeStamp() + "\n");
                    messageInput.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public String getBotResponse(String userMessage) {
        userMessage = userMessage.toLowerCase();

        // Check for specific keywords or patterns
        if (userMessage.contains("hello") || userMessage.contains("hi")) {
            return greetings[random.nextInt(greetings.length)];
        } else if (userMessage.contains("weather")) {
            return weatherResponses[random.nextInt(weatherResponses.length)];
        } else if (userMessage.contains("help")) {
            return helpResponses[random.nextInt(helpResponses.length)];
        } else if (userMessage.contains("how are you")) {
            return "I'm doing great, thank you for asking!";
        } else if (userMessage.contains("bye")) {
            return "Goodbye! Take care!";
        } else {
            return randomResponses[random.nextInt(randomResponses.length)];
        }
    }

    private String getTimeStamp() {
        // Get the current time and format it for the timestamp
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
