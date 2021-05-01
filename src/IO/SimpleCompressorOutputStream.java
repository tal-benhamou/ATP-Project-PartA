package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
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
        out.close();

    }

    public int tobyte(byte[] b, int index, int count){
        if (count == 0) {
            b[index] = -128;
            index++;
        }
        while(count > 0){
            if (count - 255 >= 0 ) {
                b[index] = 127;
                index++;
                b[index]= -128;
            }
            else if(count > 0)
                b[index] = (byte) (count - 128);
            count -= 255;
            index++;
        }
        return index;
    }

    public byte[] Compress(byte[] b){
        int countzero=0;
        int countone = 0;
        int indexarray = 7;
        int i=0;
        byte[] tmp = new byte[b.length];

        while ( i < b.length){
            if (i<7) {
                tmp[i] = b[i];
                i++;
            }
            else{
                while( i < b.length && b[i] == 0){
                    countzero++;
                    i++;
                }
                indexarray = tobyte(tmp, indexarray, countzero);

                while ( i < b.length && b[i] == 1){
                    countone++;
                    i++;
                }
                indexarray = tobyte(tmp, indexarray, countone);
                countone = 0;
                countzero = 0;
            }
        }

        byte[] result = new byte[indexarray];
        for (int j = 0; j < indexarray; j++) {
            result[j] = tmp[j];
        }
        return result;
    }
}
