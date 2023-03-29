package com.mycompany.alurastickers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class StickerGenerator {
    public void creat(InputStream inputStream, String nomeArquivo) throws Exception{
        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        // cria nova imagem com transparência (em memória)
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        
        int newHeight = height + 200;
        
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // copia a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        
        // configurar a fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        
        // escrever uma frase na nova imagem
        graphics.drawString("FILME", 0, newHeight - 100);
        
        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File(nomeArquivo));
    }
 
}





