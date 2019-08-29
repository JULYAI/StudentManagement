package net.student.bean.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.student.bean.bean.Student;
import net.student.bean.service.impl.StudentService;
import net.student.bean.service.impl.StudentServiceImpl;


public class AddStudentFrame extends JFrame{
    /**
     * 声明面板
     */
    private JPanel panel;
    private JPanel pnlCenter;
    private JPanel pnlRow1;
    private JPanel pnlRow2;
    private JPanel pnlRow3;
    private JPanel pnlRow4;
    private JPanel pnlSouth;

    /**
     * 声明标签
     */
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblSex;
    private JLabel lblCourse;

    /**
     * 声明文本框
     */
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSex;
    private JTextField txtCourse;


    /**
     * 声明按钮
     */
    private JButton btnExit;
    private JButton btnOK;
    private JButton btnCancel;

    /**
     * 当前记录行号
     */
    private int currentRow;
    /**
     * 学生列表
     */
    private List<Student> students;

    /**
     * 构造方法
     *
     * @param title
     */
    public AddStudentFrame(String title) {
        super(title);
        initGUI();
    }

    /**
     * 初始化用户界面
     */
    private void initGUI() {
        // 创建组件
        panel = (JPanel) getContentPane();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();
        pnlRow1 = new JPanel();
        pnlRow2 = new JPanel();
        pnlRow3 = new JPanel();
        pnlRow4 = new JPanel();


        pnlRow1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow3.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRow4.setLayout(new FlowLayout(FlowLayout.LEFT));


        lblId = new JLabel("学号：");
        lblName = new JLabel("姓名：");
        lblSex = new JLabel("性别：");
        lblCourse = new JLabel("课程：");


        txtId = new JTextField(20);
        txtName = new JTextField(20);
        txtSex = new JTextField(20);
        txtCourse = new JTextField(20);
        btnOK = new JButton("确定[O]");
        btnCancel = new JButton("取消[C]");
        btnExit = new JButton("退出[X]");
        btnOK.setMnemonic(KeyEvent.VK_O);
        btnCancel.setMnemonic(KeyEvent.VK_C);
        btnExit.setMnemonic(KeyEvent.VK_X);

        // 添加组件
        panel.setLayout(new BorderLayout());
        panel.add(pnlCenter, BorderLayout.CENTER);
        panel.add(pnlSouth, BorderLayout.SOUTH);
        pnlCenter.setLayout(new GridLayout(4, 1));
        pnlCenter.add(pnlRow1);
        pnlCenter.add(pnlRow2);
        pnlCenter.add(pnlRow3);
        pnlCenter.add(pnlRow4);

        pnlRow1.add(lblId);
        pnlRow1.add(txtId);
        pnlRow2.add(lblName);
        pnlRow2.add(txtName);
        pnlRow3.add(lblSex);
        pnlRow3.add(txtSex);
        pnlRow4.add(lblCourse);
        pnlRow4.add(txtCourse);


        pnlSouth.add(btnOK);
        pnlSouth.add(btnCancel);
        pnlSouth.add(btnExit);

        // 设置窗口属性
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        // 【确定】按钮单击事件
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建学生对象
                Student student = new Student();
                student.setId(txtId.getText().trim());
                student.setName(txtName.getText().trim());
                student.setSex(txtSex.getText().trim());
                student.setMyCourses(txtCourse.getText().trim());

                // 创建学生服务对象
                StudentService studentService = new StudentServiceImpl();
                // 添加学生记录
                int count = studentService.addStudent(student);
                // 判断是否添加成功
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "添加记录成功！", "增加学生记录", JOptionPane.INFORMATION_MESSAGE);
                    txtId.setText("");
                    txtName.setText("");
                    txtSex.setText("");
                    txtCourse.setText("");

                    txtId.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "添加记录失败！", "增加学生记录", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtId.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtId.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "学号不能为空！", "增加学生记录", JOptionPane.WARNING_MESSAGE);
                    txtId.requestFocus();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
            }
        });


        // 【取消】按钮单击事件
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtName.setText("");
                txtSex.setText("");
                txtCourse.setText("");

                txtId.requestFocus();
            }
        });

        // 【退出】按钮单击事件
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // 文本框按键事件
        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 13) {
                    if (!txtId.getText().trim().equals("")) {
                        txtName.requestFocus();
                    }
                }
            }
        });

        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    txtSex.requestFocus();
                }
            }
        });

        txtSex.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    txtCourse.requestFocus();
                }
            }
        });



        txtCourse.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                }
            }
        });
    }

    /**
     * 判断一个字符串是否全是数字
     *
     * @param str
     * @return
     */
    boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
