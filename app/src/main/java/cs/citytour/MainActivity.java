package cs.citytour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlaceButtonClicked(View v){
        int id = 0;
        switch(v.getId()){
            case R.id.place1:
                id = 1;
                break;
            case R.id.place2:
                id = 2;
                break;
            case R.id.place3:
                id = 3;
                break;
            case R.id.place4:
                id= 4;
                break;
        }

        Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
        intent.putExtra("PlaceNumber", id);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast toast = Toast.makeText(getApplicationContext(), "You've Just Watched Place " + data.getStringExtra("PlaceName"), Toast.LENGTH_LONG);
        toast.show();
    }
}
