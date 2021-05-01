package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{

    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] justmap = new byte[b.length - 7];
        int i = 0;
        int eight;
        int count, num;
        byte[] metadata = new byte[7];
        in.read(metadata,0,7);

        while ((count = in.read(b)) != -1){
            for (int j = 0 ; j < count ; j++) {
                eight = 8;
                num = b[j] + 128;
                while (num > 0 || eight > 0) {
                    if (j != count - 1) {
                        justmap[i] = (byte) (num % 2);
                        num = num / 2;
                        i++;
                        eight--;
                    }
                    else if(num>0){
                        justmap[i] = (byte) (num % 2);
                        num = num / 2;
                        i++;
                        eight--;
                    }
                    else
                        eight--;
                }
            }
        }
        for (int k = 0; k < b.length; k++){
            if (k<7)
                b[k] = metadata[k];
            else
                b[k] = justmap[k - 7];
        }
        in.close();
        return 1;
    }
}
