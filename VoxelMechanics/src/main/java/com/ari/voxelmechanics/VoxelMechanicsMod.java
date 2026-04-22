package com.ari.voxelmechanics;

import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.event.events.player.PlayerInteractEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import com.ari.voxelmechanics.systems.VoxelMechanicsSystem;

public class VoxelMechanicsMod extends JavaPlugin {
    
    public VoxelMechanicsMod(JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void start0() {
        System.out.println("=============================================");
        System.out.println("[VoxelMechanics] NÚCLEO DE ENGENHARIA ATIVO!");
        System.out.println("=============================================");
        
        VoxelMechanicsSystem mechanicsSystem = new VoxelMechanicsSystem();
        
        var eventBus = HytaleServer.get().getEventBus();
        eventBus.registerGlobal(PlayerInteractEvent.class, mechanicsSystem::onPlayerClickBench);
        
        System.out.println("[VoxelMechanics] Mod registrado e pronto para testes.");
    }
}
