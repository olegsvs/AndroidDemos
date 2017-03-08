package cm.android.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Android ContentProvider简单使用
 *
 * @author miaowei
 */
public class MainActivity extends Activity {

    private Button btn_addButton;
    private Button btn_queryButton;

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);


        btn_addButton = (Button) findViewById(R.id.btn_add);
        btn_queryButton = (Button) findViewById(R.id.btn_query);


        listView = (ListView) findViewById(R.id.listview);

        btn_addButton.setOnClickListener(onClickListener);
        btn_queryButton.setOnClickListener(onClickListener);
        listView.setAdapter(adapter);
    }


    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add:
                    insertRecord("MyUser");
                    break;
                case R.id.btn_query:
                    arrayList.clear();
                    displayRecords();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 添加数据记录
     */
    private void insertRecord(String userName) {
        ContentValues values = new ContentValues();
        values.put(MyUsers.User.USER_NAME, userName);

        getContentResolver().insert(MyUsers.User.CONTENT_URL, values);
    }

    /**
     * 查询数据记录
     */
    private void displayRecords() {

        String colums[] = new String[]{MyUsers.User._ID, MyUsers.User.USER_NAME};
        Uri myUri = MyUsers.User.CONTENT_URL;

        Cursor cursor = managedQuery(myUri, colums, null, null, null);
        if (cursor.moveToFirst()) {

            String idString = null;
            String userNameString = null;
            do {

                idString = cursor.getString(cursor.getColumnIndex(MyUsers.User._ID));
                userNameString = cursor.getString(cursor.getColumnIndex(MyUsers.User.USER_NAME));
                arrayList.add(idString + " " + userNameString);

                Toast.makeText(getApplicationContext(), idString + " " + userNameString, Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessage(1);
            } while (cursor.moveToNext());
        }

    }

    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }

        }
    };

}
