import android.media.Image;
import android.media.SoundPool;

/**
 * Created by Duong Thu Hien on 6/2/2018.
 */

public class NewWord {
    int id;
    Image Img_Image;
    SoundPool sound_NewWord;
    String JNewWord;
    String VNewWord;

    public NewWord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImg_Image() {
        return Img_Image;
    }

    public void setImg_Image(Image img_Image) {
        Img_Image = img_Image;
    }

    public SoundPool getSound_NewWord() {
        return sound_NewWord;
    }

    public void setSound_NewWord(SoundPool sound_NewWord) {
        this.sound_NewWord = sound_NewWord;
    }

    public String getJNewWord() {
        return JNewWord;
    }

    public void setJNewWord(String JNewWord) {
        this.JNewWord = JNewWord;
    }

    public String getVNewWord() {
        return VNewWord;
    }

    public void setVNewWord(String VNewWord) {
        this.VNewWord = VNewWord;
    }
}
