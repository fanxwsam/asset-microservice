package com.asset.security.oauthserver.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class PasswordChange {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

    public PasswordChange() {

    }

    public PasswordChange(String currentPasswordIn, String newPasswordIn, String confirmNewPasswordIn) {
        currentPassword = currentPasswordIn;
        newPassword = newPasswordIn;
        confirmNewPassword = confirmNewPasswordIn;
    }

    @Schema(description = "Current user credential password.")
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Schema(description = "New password to change to.")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Schema(description = "Confirm new password to change to.")
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public boolean checkNewPassword() {
        return newPassword.equals(confirmNewPassword);
    }
}
