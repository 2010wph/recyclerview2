package com.example.loginapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.Bean.User;
import com.example.loginapp.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private RecyclerView userRecyclerView;
    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取布局中的控件
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        userRecyclerView = findViewById(R.id.userRecyclerView);

        // 初始化用户列表
        userList = new ArrayList<>();
        userList.add(new User("用户名", "密码"));

        // 创建用户列表适配器
        userAdapter = new UserAdapter(userList);

        // 设置RecyclerView的布局管理器和适配器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        userRecyclerView.setLayoutManager(layoutManager);
        userRecyclerView.setAdapter(userAdapter);

        // 登录按钮点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidCredentials(username, password)) {
                    userList.add(new User(username, password));
                    userAdapter.notifyItemInserted(userList.size() - 1);
                    clearInputFields();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // 在实际应用中，可以自定义逻辑来验证用户名和密码的合法性
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
    }

    private void clearInputFields() {
        usernameEditText.setText("");
        passwordEditText.setText("");
    }
}
