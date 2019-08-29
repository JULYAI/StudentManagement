package net.student.bean.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class BrowseStudentsFrame extends  JFrame{
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
    private JButton btnTop;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnBottom;
    private JButton btnExit;

    /**
     * 当前记录行号
     */
    private int currentRow;
    /**
     * 学生列表
     */
    private List<Student> students;
    /**
     * 创建学生服务对象
     */
    StudentService studentService;

    /**
     * 构造方法
     *
     * @param title
     */
    public BrowseStudentsFrame(String title) {
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
        lblCourse = new JLabel("系部：");

        txtId = new JTextField(40);
        txtName = new JTextField(40);
        txtSex = new JTextField(40);
        txtCourse = new JTextField(40);

        txtId.setEditable(false);
        txtName.setEditable(false);
        txtSex.setEditable(false);
        txtCourse.setEditable(false);

        btnTop = new JButton("第一条[T]");
        btnPrevious = new JButton("上一条[P]");
        btnNext = new JButton("下一条[N]");
        btnBottom = new JButton("最后一条[B]");
        btnExit = new JButton("退出[X]");
        btnTop.setMnemonic(KeyEvent.VK_T);
        btnPrevious.setMnemonic(KeyEvent.VK_P);
        btnNext.setMnemonic(KeyEvent.VK_N);
        btnBottom.setMnemonic(KeyEvent.VK_B);
        btnExit.setMnemonic(KeyEvent.VK_X);

        // 添加组件
        panel.setLayout(new BorderLayout());
        panel.add(pnlCenter, BorderLayout.CENTER);
        panel.add(pnlSouth, BorderLayout.SOUTH);
        pnlCenter.setLayout(new GridLayout(7, 1));

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

        pnlSouth.add(btnTop);
        pnlSouth.add(btnPrevious);
        pnlSouth.add(btnNext);
        pnlSouth.add(btnBottom);
        pnlSouth.add(btnExit);

        // 设置窗口属性
        setSize(500, 300);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 创建学生服务对象
        studentService = new StudentServiceImpl();
        // 获取全部学生列表
        students = studentService.findAllStudents();
        // 判断是否有学生记录
        if (students.size() > 0) {
            // 设置当前记录号
            currentRow = 1;
            // 设置窗口标题
            setTitle("浏览学生表记录" + " && 当前记录：" + currentRow);
            // 填充窗口各文本框数据
            fillFrameData(currentRow);
        } else {
            JOptionPane.showMessageDialog(null, "表中没有记录！", "浏览学生表记录", JOptionPane.ERROR_MESSAGE);
            btnTop.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(false);
            btnBottom.setEnabled(false);
        }

        // 【第一条】按钮单击事件
        btnTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置当前记录号
                currentRow = 1;
                // 填充当前记录数据
                fillFrameData(currentRow);
            }
        });

        // 【上一条】按钮单击事件
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow > 1) {
                    // 设置当前记录号
                    currentRow--;
                    // 填充当前记录数据
                    fillFrameData(currentRow);
                } else {
                    JOptionPane.showMessageDialog(null, "已到第一条记录！", "浏览学生表记录", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 【下一条】按钮单击事件
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow < students.size() - 1) {
                    // 设置当前记录号
                    currentRow++;
                    // 填充当前记录数据
                    fillFrameData(currentRow);
                } else {
                    JOptionPane.showMessageDialog(null, "已到最后一条记录！", "浏览学生表记录", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 【最后一条】按钮单击事件
        btnBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置当前记录号
                currentRow = students.size() - 1;
                // 填充当前记录数据
                fillFrameData(currentRow);
            }
        });

        // 【退出】按钮单击事件
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * 将当前记录数据填充窗口各文本框
     *
     * @param currentRow
     */
    private void fillFrameData(int currentRow) {
        if (currentRow > 0) {
            setTitle("浏览学生表记录" + " && 当前记录：" + currentRow);
            txtId.setText(students.get(currentRow).getId());
            txtName.setText(students.get(currentRow).getName());
            txtSex.setText(students.get(currentRow).getSex());
            txtCourse.setText(students.get(currentRow).getMyCourses() + "");
        }
    }
}

