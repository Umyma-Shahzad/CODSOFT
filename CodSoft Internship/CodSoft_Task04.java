import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class QuizApp extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton submitButton;
    private JButton startButton;
    private JLabel timerLabel;
    private Timer timer;
    private int currentQuestionIndex;
    private int score;

    private String[] questions = {
            "What is the capital of France?",
            "Which river is the longest in the world?",
            "Which continent is known as the Land of the Rising Sun?",
            "What is the largest desert in the world?",
            "Which country is known as the Land of a Thousand Lakes?"
    };

    private String[][] options = {
            { "Paris", "London", "Berlin", "Madrid" },
            { "Nile", "Amazon", "Yangtze", "Mississippi" },
            { "Asia", "Africa", "Europe", "Australia" },
            { "Sahara", "Arabian", "Gobi", "Kalahari" },
            { "Finland", "Canada", "Russia", "Sweden" }
    };

    private int[] answers = { 0, 0, 0, 2, 0 };

    private JPanel startPanel;
    private ButtonGroup optionGroup;

    public QuizApp() {
        setTitle("Geography Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230)); 
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.setBackground(new Color(173, 216, 230)); 

        JLabel startLabel = new JLabel(
                "<html><div style='text-align: center; color: black; font-size: 14pt;'><b>Ready for your geography quiz?</b></div></html>",
                JLabel.CENTER);

        startButton = new JButton("Start Now");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });

        startPanel.add(startLabel, BorderLayout.CENTER);
        startPanel.add(startButton, BorderLayout.SOUTH);

        add(startPanel);
    }

    private void startQuiz() {
        remove(startPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 1));
        centerPanel.setBackground(new Color(173, 216, 230)); 

        questionLabel = new JLabel("", JLabel.CENTER);
        timerLabel = new JLabel("", JLabel.RIGHT);
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setSelected(false);
            optionGroup.add(optionButtons[i]);
            centerPanel.add(optionButtons[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        submitButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        add(questionLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
        add(timerLabel, BorderLayout.EAST);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAnswer();
            }
        });

        currentQuestionIndex = 0;
        score = 0;

        displayNextQuestion();

        revalidate();
        repaint();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);

            optionGroup.clearSelection();

            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[currentQuestionIndex][i]);
                optionButtons[i].setEnabled(true);
            }

            submitButton.setEnabled(true);
            startButton.setEnabled(false);
            startTimer();
        } else {
            displayResultScreen();
        }
    }

    private void submitAnswer() {
        timer.stop();
        submitButton.setEnabled(false);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setEnabled(false);
        }

        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption == answers[currentQuestionIndex]) {
            score++;
            JOptionPane.showMessageDialog(this, "Correct Answer!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Answer. Correct answer: " +
                    options[currentQuestionIndex][answers[currentQuestionIndex]]);
        }

        currentQuestionIndex++;
        displayNextQuestion();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            int timeLeft = 60;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft >= 0) {
                    updateTimerLabel(timeLeft);
                    timeLeft--;
                } else {
                    timer.stop();
                    submitAnswer();
                }
            }
        });

        timer.start();
    }

    private void updateTimerLabel(int secondsLeft) {
        timerLabel.setText("Time Left: " + secondsLeft + "s");
        if (secondsLeft <= 10) {
            timerLabel.setForeground(Color.RED);
        } else {
            timerLabel.setForeground(Color.BLACK);
        }
    }

    private void displayResultScreen() {
        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\nFinal Score: " + score + " out of " + questions.length,
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizApp quizApp = new QuizApp();
            quizApp.setVisible(true);
        });
    }
}
