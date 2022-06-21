package ru.nsu.panova.lab5.chat.client.entranceWindow;

import ru.nsu.panova.lab5.chat.client.Client;
import ru.nsu.panova.lab5.chat.client.Constants;
import ru.nsu.panova.lab5.chat.client.mainWindow.LoaderMainWindow;
import ru.nsu.panova.lab5.chat.client.observer.Observable;

import java.io.*;
import java.net.Socket;

public class ModelEntranceWindow extends Observable {
    public void connectToServer(String nameUser) {
        try {
            Socket clientSocket = new Socket(Constants.HOST_NAME, Constants.SOCKET);
            LoaderMainWindow loaderMainWindow = new LoaderMainWindow();

            loaderMainWindow.setClientSocket(clientSocket);
            loaderMainWindow.setUserName(nameUser);
            Client.setNewLoader(loaderMainWindow);
        } catch (IOException e) {
            notifyObservers();
        }

    }
}
