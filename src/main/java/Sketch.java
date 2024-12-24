import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Sketch extends JFrame {

    private ArrayList<StrokeShape> strokes;
    private Color currentColor;
    private int strokeThickness;
    protected  JButton saveButton;
    protected JPanel drawingPanel;

    public Sketch() {
        // Initialize the JFrame
        setTitle("AnnotateIt");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize drawing variables
        strokes = new ArrayList<>();
        currentColor = Color.BLACK;
        strokeThickness = 5;

        // Create a drawing panel
         drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Enable anti-aliasing for smoother drawing
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw all strokes
                for (StrokeShape stroke : strokes) {
                    g2d.setColor(stroke.color);
                    g2d.setStroke(new BasicStroke(stroke.thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(stroke.start.x, stroke.start.y, stroke.end.x, stroke.end.y);
                }
            }
        };

        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setPreferredSize(new Dimension(800, 600));

        // Add mouse listeners for drawing
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point endPoint = e.getPoint();
                strokes.add(new StrokeShape(startPoint, endPoint, currentColor, strokeThickness));
                startPoint = endPoint;
                drawingPanel.repaint();
            }
        };
        drawingPanel.addMouseListener(mouseAdapter);
        drawingPanel.addMouseMotionListener(mouseAdapter);

        add(drawingPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Color chooser button
        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(e -> {
            Color chosenColor = JColorChooser.showDialog(this, "Pick a Color", currentColor);
            if (chosenColor != null) {
                currentColor = chosenColor;
            }
        });
        controlPanel.add(colorButton);

        // Thickness slider
        JLabel thicknessLabel = new JLabel("Thickness:");
        controlPanel.add(thicknessLabel);
        JSlider thicknessSlider = new JSlider(1, 20, 5); // Min 1, Max 20, Default 5
        thicknessSlider.addChangeListener(e -> strokeThickness = thicknessSlider.getValue());
        controlPanel.add(thicknessSlider);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            strokes.clear();
            drawingPanel.repaint();
        });
        controlPanel.add(clearButton);

        // Save button
        saveButton = new JButton("Save");

        controlPanel.add(saveButton);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    protected String saveImage(JPanel panel, String userName) throws Exception {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();

        // Save the image to a file
        String filePath = User.USERs_FOLDER_PATH+ File.separator+ userName +File.separator+"sketch"+ Math.random()+".png";
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);
        return filePath;
    }


    // Helper class to represent a stroke
    private static class StrokeShape {
        Point start, end;
        Color color;
        int thickness;

        public StrokeShape(Point start, Point end, Color color, int thickness) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.thickness = thickness;
        }
    }
}
