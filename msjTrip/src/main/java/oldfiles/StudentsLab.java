package oldfiles;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Singleton
public class StudentsLab {

    private static StudentsLab sStudentsLab;
    private Context mAppContext;
    private ArrayList<String> mStudents;

    private StudentsLab(Context appContext) {

        mAppContext = appContext;
        String Array[] = {"Monica Kraft", "Wenhan Fang", "Sathvik Vivek", "Yu-Cheng Chou", "Yu-Ting Chou", "Jemmy Zhou", "Raymong Yin",
                "JianXiang Liu", "Nikhil Pathania", "Allison Xu", "Charles Xu", "Sharleen Zhou", "Chuck Goodman", "Anne Riley", "Roy Ho",
                "Kai Goodman", "Brandon Chen", "Stanley Zhang", "Tiernan Barrie", "Michelle Dalarossa", "Rushil Chakrabarty", "Helen Chang",
                "Sandra Kaye", "Allison Chan", "April Huang", "Alan Liu", "Akhil Reddy", "Hetav Gore", "Nishir Shelat", "William Ho", "Graciela Friend",
                "Samutratankul Mujarin", "Howard Mai", "Grant Fu", "Mackenzie Lim", "Sarah Chong", "Trevor Wu", "Sai Goli", "William Kim", "Nina Vasan",
                "Dorothy Du", "Yu Shu", "QiFei Teng", "Dorothy Griswold", "Andrew Zhang", "Waylon Peng", "Rahul Chatwani", "Nathon Ma", "Amanda Wang", "TCJ Cheng",
                "Kevin Zhu", "Kevin Yu", "Albert Stanley", "NingNing Zhou", "Janet Baker", "Sean Li", "Dylan Lim", "Jelena Lee", "Alison Chen", "Shagun Srivastava",
                "Claire Yung", "Joshua Zeng", "Anthony Herget", "Ramona Gonzalez", "Marcos Jung", "Roselyn Jung", "Jean Jea", "Emily Xu", "Angela Yang", "Mallika Chatterjee",
                "Tanushri Sundar", "Kevin Zhang", "Irene Yin", "Vivasvan Vykunta", "Zachary Kekoa", "Alexia Kekoa", "Herkea Jea", "Samuel Chou", "Aneri Parikh", "Seona Patel",
                "Kenneth Leung", "Alex Yin", "Rohan Nair", "Shiva Ramani", "Nicole Hsu", "Derek Xia", "Akshita Gandra", "Teresa Hsu"};
        mStudents = new ArrayList<String>(Arrays.asList(Array));
        Collections.sort(mStudents, String.CASE_INSENSITIVE_ORDER);


    }

    public static StudentsLab get(Context c) {
        if (sStudentsLab == null) {
            sStudentsLab = new StudentsLab(c.getApplicationContext());
        }
        return sStudentsLab;
    }

    public ArrayList<String> getmStudents() {
        return mStudents;
    }


}
