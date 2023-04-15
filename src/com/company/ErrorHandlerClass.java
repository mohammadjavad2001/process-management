package com.company;

import javax.swing.*;

public class ErrorHandlerClass {
    FileManagementClass fileManagementClass = new FileManagementClass();

    public boolean ifFileExist(JFrame frame) {
        if (fileManagementClass.fileExist())
            return true;

        Object[] options = {"Yes, Of Course", "No, Thanks"};
        int n = JOptionPane.showOptionDialog(frame,
                "Error has been Occur! Would you like to fix it automatically?\nCause of error: File Not Found!",
                "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if (n == 0) {
            fileManagementClass.createFile();
            if (fileManagementClass.fileExist()) {
                JOptionPane.showMessageDialog(null, "Fixed!\nPlease try again to click Run.", "Solved!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Not Fixed. Please Install app again!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            return false;
        }
    }
}
