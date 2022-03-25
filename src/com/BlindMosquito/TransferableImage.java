package com.BlindMosquito;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TransferableImage implements Transferable {
  private Image image;

  public TransferableImage(Image image) { this.image = image; }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] { DataFlavor.imageFlavor };
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
    return DataFlavor.imageFlavor.equals(dataFlavor);
  }

  @Override
  public Object getTransferData(DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
    if(!DataFlavor.imageFlavor.equals(dataFlavor)) {
      throw new UnsupportedFlavorException(dataFlavor);
    }
    return image;
  }
}
