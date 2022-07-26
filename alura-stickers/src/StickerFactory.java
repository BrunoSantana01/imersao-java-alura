import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {

    public void createSticker(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem

        //InputStream inputStream = 
        // new FileInputStream(
        //     new File("entrada/TheShawshankRedemption.jpg")
        // );
        // InputStream inputStream = 
        //     new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_3.jpg")
        //     .openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem com transparencia e tamanho novo
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int novaAltura = height + (height/100) * 20;
        BufferedImage novaImagem = new BufferedImage(width, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original para a nova imagem em memoria
        Graphics2D graphics =(Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.CYAN);
        
        // escrever na imagem
        graphics.drawString("TOPZERA", 100, novaAltura-100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png",new File(nomeArquivo));
    }
}