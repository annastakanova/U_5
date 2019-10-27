package com.epam.by.service;

import com.epam.by.constant.Constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    public List<String> getFileContent(String fileName) {
        List<String> list = new ArrayList<>();
        String space = " ";
        try {
            list = Files.lines(Paths.get(fileName))
                    .flatMap(line -> Stream.of(line.split(space)))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
