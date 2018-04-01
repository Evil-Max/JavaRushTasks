package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for(Map.Entry<String, Connection> connection : connectionMap.entrySet()) {
            try {
                connection.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage ("Ошибка при отправке сообщения");
            }
        }
    }

    private static class Handler extends Thread {
        Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message answer = connection.receive();
                if (answer.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT,userName+": "+answer.getData()));
                } else {
                    ConsoleHelper.writeMessage("Ошибка - не верный тип сообщения");
                }

            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for(Map.Entry<String, Connection> conn : connectionMap.entrySet()) {
                if (!conn.getKey().equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED,conn.getKey()));
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();

                if (answer.getType() == MessageType.USER_NAME) {

                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }

        }

        @Override
        public void run() {
            String userName = null;
            if ((socket!=null) && (socket.getRemoteSocketAddress()!=null)) {
                ConsoleHelper.writeMessage("Установлено соединение с " + socket.getRemoteSocketAddress());
            }
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            } finally {
                if (userName!=null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
            }
            //super.run();
        }
    }

    public static void main(String[] args) throws IOException {
        int socketNumber = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(socketNumber);
        Socket socket = null;

        ConsoleHelper.writeMessage("Сервер запущен");
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                serverSocket.close();
                ConsoleHelper.writeMessage(e.getMessage());
                break;
            };
            (new Handler(socket)).start();

        }
    }
}
