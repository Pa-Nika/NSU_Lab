package ru.nsu.panova.lab5.client.client.mainWindow.communicatingWithServer.Command;

import ru.nsu.panova.lab5.client.client.Constants;
import ru.nsu.panova.lab5.client.client.exeption.FactoryExceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryClientCommand {
    private final Map<String, CommandInterface> commandMap = new HashMap<>();

    public void configureFactory() throws FactoryExceptions {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FACTORY_CONFIGURATION_FILE_NAME))){
            Pattern pattern = Pattern.compile(Constants.REGEX_FOR_CONFIGURATION_FACTORY);
            String str = reader.readLine();
            while (str != null) {
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    String workerKey = str.substring(matcher.start(), matcher.end());
                    if (matcher.find()) {
                        Class<?> executor = Class.forName(str.substring(matcher.start(), matcher.end()));
                        CommandInterface command = (CommandInterface) executor.getDeclaredConstructor().newInstance();

                        commandMap.put(workerKey, command);
                        str = reader.readLine();
                    } else {
                        throw (new FactoryExceptions(Constants.EXCEPTION_FACTORY_CONFIGURATION_FILE));
                    }
                } else {
                    throw (new FactoryExceptions(Constants.EXCEPTION_FACTORY_CONFIGURATION_FILE));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw (new FactoryExceptions(Constants.EXCEPTION_FACTORY_CONFIGURATION_FILE));
        }
    }

    public CommandInterface getCommand(String strCommand) {
        return commandMap.get(strCommand);
    }
}

