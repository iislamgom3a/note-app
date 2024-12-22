# note Taking app Notes 
## Authors : 
- Islam Gomaa
- Anas Osama
- Ahmed Mostafa 
- Osama Nady

**Source code**

```java
private void addImageToMarkdown() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select an Image");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp"));

    int userSelection = fileChooser.showOpenDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
        String markdownImage = String.format("![Image](%s)", imagePath);

        // Insert the image markdown at the current cursor position
        int cursorPosition = textArea.getCaretPosition();
        textArea.insert(markdownImage, cursorPosition);

        // Optionally, switch to preview mode to show the added image
        if (!isRawMode) {
            updatePreview();
        }
    }
}
```