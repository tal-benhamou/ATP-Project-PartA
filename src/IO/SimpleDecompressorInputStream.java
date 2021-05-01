package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] result = new byte[b.length - 7];
        int even = 0;
        int index = 0;
        int count;
        byte[] tmp = new byte[7];
        in.read(tmp,0,7);
        // Decompressing
        while ((count = in.read(b))!= -1){
            for (int i=0; i < (count); i++){
                if (even%2 == 0){
                    while( i < b.length && (b[i] + 128)  > 0){
                        result[index] = 0;
                        index++;
                        b[i]--;
                    }
                }
                else{
                    while( i < b.length && (b[i] + 128) > 0){
                        result[index] = 1;
                        index++;
                        b[i]--;
                    }
                }
                even++;
            }
        }

        for (int i = 0 ; i< b.length; i++){
            if(i<7){
                b[i]=tmp[i];
            }
            else
                b[i]=result[i-7];
        }

        in.close();
        return 1;
    }
}
