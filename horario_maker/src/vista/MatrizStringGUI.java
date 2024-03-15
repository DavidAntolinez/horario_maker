package vista;
import javax.swing.*;
import java.awt.*;

public class MatrizStringGUI extends JFrame {
    private String[][] matriz;

    public MatrizStringGUI(String[][] matriz) {
        this.matriz = matriz;

        // Configurar la ventana
        setTitle("Matriz de Strings");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear y agregar el componente de la matriz
        MatrizPanel matrizPanel = new MatrizPanel(matriz);
        add(matrizPanel);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Clase interna para representar el panel de la matriz
    private class MatrizPanel extends JPanel {
        private String[][] matriz;

        public MatrizPanel(String[][] matriz) {
            this.matriz = matriz;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int filaHeight = getHeight() / matriz.length;
            int columnaWidth = getWidth() / matriz[0].length;

            // Dibujar la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    String elemento = matriz[i][j];
                    int x = j * columnaWidth;
                    int y = i * filaHeight;

                    // Dibujar el rectángulo
                    g.drawRect(x, y, columnaWidth, filaHeight);

                    // Dibujar el texto centrado en el rectángulo
                    FontMetrics fm = g.getFontMetrics();
                    int textoX = x + (columnaWidth - fm.stringWidth(elemento)) / 2;
                    int textoY = y + (filaHeight - fm.getHeight()) / 2 + fm.getAscent();
                    g.drawString(elemento, textoX, textoY);
                }
            }
        }
    }

    // public static void main(String[] args) {
    //     // Ejemplo de uso
    //     String[][] ejemploMatriz = {
    //             {"A", "B", "C"},
    //             {"D", "E", "F"},
    //             {"G", "H", "I"}
    //     };

    //     SwingUtilities.invokeLater(() -> new MatrizStringGUI(ejemploMatriz));
    // }
}
