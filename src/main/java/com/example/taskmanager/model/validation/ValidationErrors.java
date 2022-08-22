package com.example.taskmanager.model.validation;

public class ValidationErrors {

    private String taskNameError;
    private String taskDescriptionError;
    private String usernameError;
    private boolean hasErrors;

    public String getTaskNameError() {
        return taskNameError;
    }

    public void setTaskNameError(String taskNameError) {
        this.taskNameError = taskNameError;
    }

    public String getTaskDescriptionError() {
        return taskDescriptionError;
    }

    public void setTaskDescriptionError(String taskDescriptionError) {
        this.taskDescriptionError = taskDescriptionError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
}
