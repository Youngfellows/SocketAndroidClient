package com.byron.socketclicent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vilyever.socketclient.util.IPUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvIp;
    private EditText mEdServerIp;
    private EditText mEdServerPort;
    private Button mBtnSendTV;
    private TextView mTvReceiveServer;
    private Button mBtnConnectTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mTvIp = findView(R.id.tv_ip); //显示本机IP
        mEdServerIp = findView(R.id.edt_tv_pi); //服务端IP
        mEdServerPort = findView(R.id.edt_tv_port);//服务端端口
        mBtnSendTV = findView(R.id.btn_send_voice); //发送到TV端
        mTvReceiveServer = findView(R.id.tv_receive_server); //接收服务端的消息
        mBtnConnectTv = findView(R.id.btn_connect_tv); //连接TV端

    }

    private void initData() {
//        getTestClient().connect();//服务端
        initLocalhostIp();

    }

    private void initLocalhostIp() {
        String ipAddress = IPUtil.getLocalIPAddress(true);
        mTvIp.setText("客户端IP: " + ipAddress);
    }

    private void initListener() {
        mBtnConnectTv.setOnClickListener(this);
        mBtnSendTV.setOnClickListener(this);
    }


    private TestClient testClient;

    protected TestClient getTestClient(String ip, String port) {
        if (this.testClient == null) {
            this.testClient = new TestClient(ip,port);
        }
        return this.testClient;
    }

    protected <T extends View> T findView(int viewId) {
        return (T) findViewById(viewId);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect_tv:
                // TODO: 2017/10/29 连接TV端
                showToast(" 连接TV端");
                getTestClient(mEdServerIp.getText().toString(), mEdServerPort.getText().toString()).connect();//服务端
                break;

            case R.id.btn_send_voice:
                // TODO: 2017/10/29 发送数据到TV端
                showToast("发送数据到TV端");
                break;

            default:
                break;
        }
    }
}
