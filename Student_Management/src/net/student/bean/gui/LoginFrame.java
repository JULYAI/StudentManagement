package net.student.bean.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.student.bean.app.Application;
import net.student.bean.bean.User;
import net.student.bean.service.impl.UserService;
import net.student.bean.service.impl.UserServiceImpl;

public class LoginFrame extends JFrame{
    //用户名
    private String username;
    //密码
    private String password;

     //标签
    private JLabel lblUsername;
    private JLabel lblPassword;

    //文本框
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    //按钮
    private JButton btnOK;
    private JButton btnCancel;

    //面板
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    /**
     * 构造方法
     *
     * @param title
     */
    public LoginFrame(String title) {
        super(title);
        // 调用初始化界面方法
        initGUI();
    }

    /**
     * 初始化界面
     */
    private void initGUI() {
        // 实例化控件
        lblUsername = new JLabel("用户名：");
        lblPassword = new JLabel("密  码：");
        txtUsername = new JTextField("", 15);
        txtPassword = new JPasswordField("", 15);
        btnOK = new JButton("确定[O]");
        btnCancel = new JButton("取消[C]");
        panel = (JPanel) getContentPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        // 设置主面板为网格布局
        panel.setLayout(new GridLayout(3, 1));
        // 将三行面板添加到主面板
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        // 将控件分别添加到三行面板
        panel1.add(lblUsername);
        panel1.add(txtUsername);
        panel2.add(lblPassword);
        panel2.add(txtPassword);
        panel3.add(btnOK);
        panel3.add(btnCancel);

        // 设置窗口与控件属性
        setSize(250, 200); // 设置窗口大小
        setLocationRelativeTo(null);// 让窗口居中
        setResizable(false); // 窗口不可调整大小
        pack(); // 使窗口恰好容纳组件
        setVisible(true); // 让窗口可见
        btnOK.setMnemonic(KeyEvent.VK_O); // 设置热键字母
        btnCancel.setMnemonic(KeyEvent.VK_C);// 设置热键字母

        /*
         * 注册监听器，编写事件处理代码 采用匿名内部类方式来实现
         */
        // 确定按钮单击事件
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // 确定按钮按键事件
        btnOK.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    login();
                }
            }
        });

        // 取消按钮单击事件
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 用户名文本框按键事件
        txtUsername.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    txtPassword.requestFocus();
                }
            }
        });

        // 密码文本框按键事件
        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    login();
                }
            }
        });
    }

    /**
     * 登录方法
     */
    private void login() {
        // 获取用户名
        username = txtUsername.getText().trim();
        // 获取密码
        password = new String(txtPassword.getPassword());
        // 创建用户服务对象
        UserService userService = new UserServiceImpl();
        // 用户登录
        User user = userService.login(username, password);
        // 判断是否登录成功
        if (user != null) {
            // 隐藏登录窗口
            Application.loginFrame.setVisible(false);
            // 保存用户名
            Application.username = user.getUserName();
            // 提示用户登录成功
            JOptionPane.showMessageDialog(null, "欢迎使用学生信息管理系统" + "！",
                    "学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
            // 显示系统主窗口
            Application.mainFrame = new MainFrame("学生信息管理系统" );
            // 释放登录窗口
            Application.loginFrame.dispose();
        } else {
            // 隐藏登录窗口
            Application.loginFrame.setVisible(false);
            // 提示用户登录失败
            JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入！", "学生信息管理系统", JOptionPane.ERROR_MESSAGE);
            // 显示登录窗口
            Application.loginFrame.setVisible(true);
            // 用户名文本全部选中
            txtUsername.selectAll();
            // 密码文本全部选中
            txtPassword.selectAll();
            // 用户名文本框获取焦点
            txtUsername.requestFocus();
        }
    }

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        // 设置应用程序登录窗口
        Application.loginFrame = new LoginFrame("登录");
    }

}
