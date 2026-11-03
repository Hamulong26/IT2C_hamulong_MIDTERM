package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.AllocationMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize database and tables on startup
        DatabaseConnection.initializeDatabase();

        // Launch interactive UI menu
        AllocationMenu menu = new AllocationMenu();
        menu.start();
    }
}