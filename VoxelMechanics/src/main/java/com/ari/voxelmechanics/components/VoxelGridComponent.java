package main.java.com.ari.voxelmechanics.components;

import java.util.Arrays;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class VoxelGridComponent implements Component<EntityStore> {
    
    private static final int SIZE = 16;
    private static final int VOLUME = SIZE * SIZE * SIZE;
    
    private byte[] voxels;

    public VoxelGridComponent() {
        this.voxels = new byte[VOLUME];
    }

    public void fill(byte materialId) {
        Arrays.fill(this.voxels, materialId);
    }

    private int getIndex(int x, int y, int z) {
        return x + (y * SIZE) + (z * SIZE * SIZE);
    }

    public void setVoxel(int x, int y, int z, byte materialId) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || z < 0 || z >= SIZE) {
            return;
        }
        this.voxels[getIndex(x, y, z)] = materialId;
    }

    public byte getVoxel(int x, int y, int z) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || z < 0 || z >= SIZE) {
            return 0;
        }
        return this.voxels[getIndex(x, y, z)];
    }
    @Override
    public VoxelGridComponent clone() {
        VoxelGridComponent copy = new VoxelGridComponent();
        System.arraycopy(this.voxels, 0, copy.voxels, 0, VOLUME);
        return copy;
    }
}
