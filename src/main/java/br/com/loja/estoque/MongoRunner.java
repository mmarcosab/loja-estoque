package br.com.loja.estoque;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class MongoRunner {

    static boolean isMongoRunning() throws IOException {
        final var containers = run("docker container ls --filter \"status=running\"");
        String line;
        try(var reader = new BufferedReader(new InputStreamReader(containers.getInputStream()))) {
            while((line = reader.readLine()) != null) {
                log.info(line);
                if(line.contains("estoque-container")) return true;
            }
        }
        return false;
    }

    static void startMongo() throws IOException, InterruptedException {
        if(SystemUtils.IS_OS_WINDOWS) run("cmd.exe /C start docker compose up");
        else if(SystemUtils.IS_OS_LINUX) run("/bin/bash -c docker compose up");
        while(!isMongoRunning()) {
            log.info("Starting mongo...");
            Thread.sleep(5000);
        }
    }

    private static Process run(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }
}
