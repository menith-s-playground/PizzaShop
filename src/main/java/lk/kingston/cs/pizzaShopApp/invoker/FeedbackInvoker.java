package lk.kingston.cs.pizzaShopApp.invoker;

import lk.kingston.cs.pizzaShopApp.command.Command;

import java.util.Stack;

public class FeedbackInvoker {
    private Stack<Command> commandHistory = new Stack<>();

    public void invoke(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No feedback to undo.");
        }
    }
}
