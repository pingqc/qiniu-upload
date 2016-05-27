import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

/**
 * Created by pingqc on 16/5/25.
 */
public class SaveImage {

    public static String saveImageFromClipboard() throws Exception {
        Image image = getImageFromClipboard();
        if (image == null) {
            throw new Exception("no image found in the clipboard");
        }
        String name = "/tmp/" + System.currentTimeMillis() + ".jpg";
        File file = new File(name);
//        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);
        ImageIO.write((RenderedImage) bufferedImage, "jpg", file);
        return name;
    }

    private static Image getImageFromClipboard() throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(null);
        if (transferable == null) {
            return null;
        } else if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
        }
        return null;
    }

}

