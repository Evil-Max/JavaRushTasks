package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

/**
 * Created by Beast on 23.09.2017.
 */
public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes it) throws IllegalArgumentException {
        if (it == ImageTypes.JPG) {
            return new JpgReader();

        } else if (it == ImageTypes.BMP) {
            return new BmpReader();

        } else if (it == ImageTypes.PNG) {
            return new PngReader();
        }
        throw new IllegalArgumentException("Неизвестный тип картинки");

    }
}
