package net.student.bean.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.student.bean.app.Application;

public class MainFrame extends JFrame{
    //菜单部分
    private JMenuBar mnbMain;

    //设置菜单
    private JMenu mnuSet;
    private JMenuItem mniExit;

    //操作菜单
    private JMenu mnuOperate;
    private JMenuItem mniAddStudent;
    private JMenuItem mniBrowseStudent;
    private JMenuItem mniEditStudent;

    //删除菜单
    private JMenu mnuDelStu;
    private JMenuItem mniDelStudentById;
    private JMenuItem mniDelStudentByCourse;

    //查询菜单
    private JMenu mnuFind;
    private JMenuItem mniFindStudentById;
    private JMenuItem mniFindStudentsByName;
    private JMenuItem mniFindStudentsByCourse;

    //面板
    private JPanel panel;
    private JPanel  pnlMain;
    private JPanel  pnlStatus;

    //背景标签
    private JLabel lblBackground;

    //图标对象
    private ImageIcon imgExit;
    private ImageIcon imgQuery;
    private ImageIcon imgBrowse;
    private ImageIcon imgBackground;

    //工具栏
    private JToolBar toolbar;

    //按钮
    private JButton btnFindStudentById;
    private JButton btnExit;
    private JButton btnBrowseStudent;

    /**
     * 构造方法
     *
     * @param title
     */
    public MainFrame(String title) {
        super(title);
        initGUI();
    }

    /**
     * 初始化用户界面
     */
    private void initGUI() {
        // 创建主菜单
        mnbMain = new JMenuBar();

        // 创建【设置】菜单及其菜单项
        mnuSet = new JMenu("系统设置[S]");
        mnuSet.setMnemonic(KeyEvent.VK_S);
        mniExit = new JMenuItem("退出系统");

        // 创建【操作】菜单及其菜单项
        mnuOperate = new JMenu("数据操作[O]");
        mnuOperate.setMnemonic(KeyEvent.VK_O);
        mniAddStudent = new JMenuItem("增加学生表记录");
        mnuDelStu = new JMenu("删除学生表记录");
        mniEditStudent = new JMenuItem("编辑学生表记录");
        mniBrowseStudent = new JMenuItem("浏览学生表记录");

        // 创建【删除学生表记录】的子菜单
        mniDelStudentById = new JMenuItem("按学号删除");
        mniDelStudentByCourse = new JMenuItem("按课程删除");

        // 创建【查询】菜单及其菜单项
        mnuFind = new JMenu("查询学生[Q]");
        mnuFind.setMnemonic(KeyEvent.VK_Q);
        mniFindStudentById = new JMenuItem("按学号查询");
        mniFindStudentsByName = new JMenuItem("按姓名查询");
        mniFindStudentsByCourse = new JMenuItem("按课程查询");

        // 创建图标对象
        imgQuery = new ImageIcon("images/query.jpg");
        imgBrowse = new ImageIcon("images/browse.jpg");
        imgExit = new ImageIcon("images/exit.jpg");

        // 创建工具栏
        toolbar = new JToolBar();
        btnBrowseStudent = new JButton("浏览学生", imgBrowse);
        btnBrowseStudent.setToolTipText("浏览学生记录");
        btnBrowseStudent.setVerticalTextPosition(AbstractButton.BOTTOM);
        btnBrowseStudent.setHorizontalTextPosition(AbstractButton.CENTER);
        btnFindStudentById = new JButton("查询学生", imgQuery);
        btnFindStudentById.setToolTipText("按学号查询学生记录");
        btnFindStudentById.setVerticalTextPosition(AbstractButton.BOTTOM);
        btnFindStudentById.setHorizontalTextPosition(AbstractButton.CENTER);
        btnExit = new JButton("退出系统", imgExit);
        btnExit.setToolTipText("退出系统");
        btnExit.setVerticalTextPosition(AbstractButton.BOTTOM);
        btnExit.setHorizontalTextPosition(AbstractButton.CENTER);
        toolbar.add(btnBrowseStudent);
        toolbar.add(btnFindStudentById);
        toolbar.add(btnExit);

        // 创建面板
        panel = (JPanel) getContentPane();
        pnlMain = new JPanel();
        pnlStatus = new JPanel();
        pnlStatus.setLayout(new GridLayout(1, 5));

        // 创建背景图片
        imgBackground = new ImageIcon("images/imgBackground.jpg");

        // 创建背景标签
        lblBackground = new JLabel(imgBackground);

        // 设置菜单栏
        setJMenuBar(mnbMain);
        // 添加【设置】菜单
        mnbMain.add(mnuSet);
        mnuSet.addSeparator();
        mnuSet.add(mniExit);

        // 添加【删除学生表记录】菜单
        mnuDelStu.add(mniDelStudentById);
        mnuDelStu.add(mniDelStudentByCourse);

        // 添加【操作】菜单
        mnbMain.add(mnuOperate);
        mnuOperate.add(mniAddStudent);
        mnuOperate.add(mniEditStudent);
        mnuOperate.add(mnuDelStu);
        mnuOperate.add(mniBrowseStudent);

        // 添加【查询】菜单
        mnbMain.add(mnuFind);
        mnuFind.add(mniFindStudentById);
        mnuFind.add(mniFindStudentsByName);
        mnuFind.add(mniFindStudentsByCourse);

        // 添加面板
        panel.setLayout(new BorderLayout());
        panel.add(toolbar, "North");
        panel.add(pnlMain, "Center");
        panel.add(pnlStatus, "South");
        pnlMain.add(lblBackground);

        // 设置窗口属性
        setSize(800, 640);
        setVisible(true);
        setLocationRelativeTo(null);

        // 关闭窗口单击事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitSystem();
            }
        });

        // 设置菜单
        // 【退出系统】菜单项单击事件
        mniExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        // 查询菜单
        // 【按学号查询】菜单项单击事件
        mniFindStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByIdFrame("按学号查询学生记录");
            }
        });

        // 【按姓名查询】菜单项单击事件
        mniFindStudentsByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByNameFrame("按姓名学生记录");
            }
        });

        // 【按课程查询】菜单项单击事件
        mniFindStudentsByCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByCourseFrame("按课程查询学生记录");
            }
        });

        // 【增加学生记录】菜单项单击事件
        mniAddStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudentFrame("增加学生表记录");
            }
        });

        // 【按学号删除学生记录】菜单项单击事件
        mniDelStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentByIdFrame("按学号删除学生记录");
            }
        });
        // 【按学号删除学生记录】菜单项单击事件
        mniDelStudentByCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentByCourseFrame("按课程删除学生记录");
            }
        });

        // 【按课程删除学生记录】菜单项单击事件
        mniDelStudentByCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentByCourseFrame("按课程删除学生记录");
            }
        });


        // 【编辑学生记录】菜单项单击事件
        mniEditStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditStudentFrame("编辑学生表记录");
            }
        });

        // 【浏览学生记录】菜单项单击事件
        mniBrowseStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BrowseStudentsFrame("浏览学生表记录");
            }
        });

        // 工具栏按钮单击事件
        // 【浏览】按钮
        btnBrowseStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrowseStudentsFrame("浏览学生表记录");
            }
        });

        // 【查询】按钮
        btnFindStudentById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudentByIdFrame("按学号查询学生记录");
            }
        });

        // 【退出】按钮
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

    }

    /**
     * 退出系统
     */
    protected void exitSystem() {
        int choice = JOptionPane.showConfirmDialog(null, "你是否要退出系统？", "学生信息管理系统", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            // 关闭当前窗口
            dispose();
            Application.mainFrame = new MainFrame("学生信息管理系统");
        }
    }
}
