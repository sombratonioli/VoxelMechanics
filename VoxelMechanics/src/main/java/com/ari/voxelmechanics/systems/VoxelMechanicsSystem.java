package com.ari.voxelmechanics.systems;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.event.events.player.PlayerInteractEvent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import com.ari.voxelmechanics.components.VoxelGridComponent;

public class VoxelMechanicsSystem {

    public void onPlayerClickBench(PlayerInteractEvent event) {
        
        if (event.getTargetBlock() == null) {
            return;
        }

        var targetRef = event.getTargetRef();
        if (targetRef == null || !targetRef.isValid()) {
            return; 
        }

        var store = targetRef.getStore();
        var registry = store.getRegistry();
        
        @SuppressWarnings("unchecked")
        ComponentType<EntityStore, VoxelGridComponent> componentType = 
            (ComponentType<EntityStore, VoxelGridComponent>) registry.getData().getComponentType(VoxelGridComponent.class.getSimpleName());
        
        if (componentType == null) {
            return;
        }

        VoxelGridComponent grid = store.getComponent(targetRef, componentType);

        if (grid == null) {
            return; 
        }

        System.out.println("[VoxelMechanics] -> Mesa do Mecânico foi clicada!");

        ItemStack handItem = event.getItemInHand();
        if (handItem == null || handItem.isEmpty()) {
            System.out.println("[VoxelMechanics] -> Mão vazia. Cancelando.");
            return; 
        }

        System.out.println("[VoxelMechanics] -> O jogador está segurando um item. Preenchendo a Matriz...");

        grid.fill((byte) 1);
        System.out.println("[VoxelMechanics] SUCESSO: A matriz 16x16x16 foi preenchida com material 1.");

        int currentQty = handItem.getQuantity();
        if (currentQty > 0) {
            System.out.println("[VoxelMechanics] Item consumido. Restam: " + (currentQty - 1));
        }
        
        event.setCancelled(true);
    }
}
