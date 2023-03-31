import java.awt.BorderLayout;
import javax.swing.*;
import java.util.ArrayList;

public class Planner {
    private static double budgetValue;

    public static void main(String[] args) {
        JFrame incomeFrame = new JFrame("Planner");
        String budgetInput = JOptionPane.showInputDialog(incomeFrame, "Введіть Ваш б'юджет:");
        budgetValue = Double.parseDouble(budgetInput);

        JFrame plannerFrame = new JFrame("Planner");
        JLabel budgetLabel = new JLabel("Ваш б'юджет: " + budgetValue);
        JButton addBudgetButton = new JButton("+");
        JPanel plannerPanel = new JPanel(new BorderLayout());
        plannerPanel.add(budgetLabel, BorderLayout.NORTH);
        plannerPanel.add(addBudgetButton, BorderLayout.WEST);

        ArrayList<String> budgetList = new ArrayList<>();
        JList<String> budgetJList = new JList<>(budgetList.toArray(new String[0])); // declare and initialize
                                                                                    // budgetJList
        JScrollPane budgetScrollPane = new JScrollPane(budgetJList);
        plannerPanel.add(budgetScrollPane, BorderLayout.CENTER);

        JFrame addBudgetFrame = new JFrame("Planner");
        JTextField budgetNameField = new JTextField(20);
        JTextField amountField = new JTextField(20);
        JRadioButton incomeRadioButton = new JRadioButton("Прибуток");
        JRadioButton expenseRadioButton = new JRadioButton("Витрата");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(incomeRadioButton);
        radioButtonGroup.add(expenseRadioButton);
        JButton addBudgetToListButton = new JButton("Додати");
        JPanel addBudgetPanel = new JPanel();
        addBudgetPanel.add(new JLabel("Назва:"));
        addBudgetPanel.add(budgetNameField);
        addBudgetPanel.add(new JLabel("Сума:"));
        addBudgetPanel.add(amountField);
        addBudgetPanel.add(incomeRadioButton);
        addBudgetPanel.add(expenseRadioButton);
        addBudgetPanel.add(addBudgetToListButton);
        addBudgetFrame.add(addBudgetPanel);
        addBudgetFrame.pack();

        addBudgetButton.addActionListener(e -> addBudgetFrame.setVisible(true));

        addBudgetToListButton.addActionListener(e -> {
            String budgetName = budgetNameField.getText();
            String amount = amountField.getText();
            if (incomeRadioButton.isSelected()) {
                budgetValue += Double.parseDouble(amount);
                budgetList.add(budgetName + " (прибуток: " + amount + ")");
            } else if (expenseRadioButton.isSelected()) {
                budgetValue -= Double.parseDouble(amount);
                budgetList.add(budgetName + " (витрата: " + amount + ")");
            }
            budgetLabel.setText("Ваш б'юджет: " + budgetValue);
            addBudgetFrame.setVisible(false);
            budgetJList.setListData(budgetList.toArray(new String[0]));
        });

        plannerFrame.add(plannerPanel);
        plannerFrame.pack();
        plannerFrame.setVisible(true);
    }
}