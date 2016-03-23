package services;


import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

public class UpdateEventsService extends GcmTaskService {



    public UpdateEventsService() {
    }


    @Override
    public int onRunTask(TaskParams taskParams) {
        Handler h = new Handler(getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UpdateEventsService.this, "onRunTask executed", Toast.LENGTH_SHORT).show();
                Log.d("MyService", "Service updated");
            }
        });
        return GcmNetworkManager.RESULT_SUCCESS;
    }

}
