package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] result = Compress(b);
        out.write(result);
        out.flush();
       // out.close();
    }

    public byte[] Compress(byte[] b) {
        byte[] compressed = new byte[b.length];
        byte compress;
        int j = 0;
        byte[] result;
        int index = 7;


        while (j < compressed.length) {
            if (j < 7) {
                compressed[j] = b[j];
                j++;
            }
            else {
                byte[] tmp2 = Arrays.copyOfRange(b, j, j+8);
                compress = 0;
                for (int i = 7; i >= 0; i--) {
                    compress = (byte) (compress << 1 | tmp2[i]);
                }
                compressed[index++] = (byte) (compress - 128);
                j+=8;
            }
        }
        if ((b.length - 7) % 8 != 0) {
            result = new byte[7 + (b.length - 7) / 8 + 1 ];// 7 : cons + number of compressings + sheerit + count : number of dipun
            System.arraycopy(compressed, 0, result, 0, result.length);
        }
        else {
            result = new byte[7 + (b.length - 7) / 8];
            System.arraycopy(compressed, 0, result, 0, result.length);
        }

        return result;
    }
}
