/* 
 * Copyright (C) 2018 Carlos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package empenofacil;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Aplicación principal
 * @author Carlos
 */
public class EmpenoFacil extends Application {
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
        Util.login();
    }

    /**
     * Lanza la aplicación principal
     * @param args Argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Regresa el Stage principal del programa
     * @return Stage principal
     */
    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        stage.getIcons().add(new Image(EmpenoFacil.class.getResourceAsStream("img/icono.png")));
        EmpenoFacil.stage = stage;
    }
}