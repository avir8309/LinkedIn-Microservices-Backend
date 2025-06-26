package com.example.LinkedIn.UserService.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash password using BCrypt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 is work factor
    }

    // Verify raw password against hashed password
    public static boolean matchPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
