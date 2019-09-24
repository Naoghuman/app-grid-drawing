/**
 * Copyright (C) 2019 - 2019 Naoghuman
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
package com.github.naoghuman.app.grid.drawing.resources;

import com.github.naoghuman.app.grid.drawing.validator.DefaultGridDrawingValidator;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.io.File;
import java.util.Optional;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.filechooser.FileSystemView;

/**
 * TODO
 * 
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 * @author  Naoghuman
 */
public class ImageImporter {
    
    private static final Optional<ImageImporter> INSTANCE = Optional.of(new ImageImporter());
    
    /**
     * TODO
     * 
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     * @author  Naoghuman
     * @return
     */
    public static final ImageImporter getDefault() {
        return INSTANCE.get();
    }
    
    private Boolean initialize = Boolean.FALSE;
    
    private FileChooser fcImportImage;
    private FileChooser fcSaveImage;
    
    private ImageImporter() {
        
    }

    private void initialize() {
        LoggerFacade.getDefault().info(ImageReader.class, "ImageImporter#initialize()"); // NOI18N
        
        fcImportImage = new FileChooser();
        fcImportImage.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        fcImportImage.setTitle("Import image"); // NOI18N
        fcImportImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(ImageFormat.ALL_IMAGES.getDescription(), ImageFormat.ALL_IMAGES.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.GIF.getDescription(),        ImageFormat.GIF.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.JPEG.getDescription(),       ImageFormat.JPEG.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.JPG.getDescription(),        ImageFormat.JPG.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.PNG.getDescription(),        ImageFormat.PNG.getExtension()));
        
        fcSaveImage = new FileChooser();
        fcSaveImage.setTitle("Save image"); // NOI18N
        fcSaveImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(ImageFormat.ALL_IMAGES.getDescription(), ImageFormat.ALL_IMAGES.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.GIF.getDescription(),        ImageFormat.GIF.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.JPEG.getDescription(),       ImageFormat.JPEG.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.JPG.getDescription(),        ImageFormat.JPG.getExtension()),
                new FileChooser.ExtensionFilter(ImageFormat.PNG.getDescription(),        ImageFormat.PNG.getExtension()));
    }
    
    public File load(final Window window) {
        LoggerFacade.getDefault().debug(ImageReader.class, "ImageImporter#load(Window): File"); // NOI18N
        
        DefaultGridDrawingValidator.requireNonNull(window);
        
        if (!initialize) {
            this.initialize();
            
            initialize = Boolean.TRUE;
        }
        
        final File file = fcImportImage.showOpenDialog(window);
        
        return file;
    }
    
    public Image read(final File file) {
        LoggerFacade.getDefault().debug(ImageReader.class, "ImageImporter#load()"); // NOI18N
        
        DefaultGridDrawingValidator.requireNonNull(file);
        
        final Image image = ImageReader.read(file);
        
        return image;
    }

    public void write(final Window window, final String name, final Image image) {
        LoggerFacade.getDefault().debug(ImageReader.class, "ImageImporter#write(Image)"); // NOI18N

        DefaultGridDrawingValidator.requireNonNullAndNotEmpty(name);
        DefaultGridDrawingValidator.requireNonNull(image);
        
        File directory = new File(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "images"); // NOI18N
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        fcSaveImage.setInitialDirectory(directory);
        fcSaveImage.setInitialFileName(name);
        
        File file = fcSaveImage.showSaveDialog(window);
        ImageWriter.write(file, image);
    }
    
}
