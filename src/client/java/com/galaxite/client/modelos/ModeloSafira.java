package com.galaxite.client.modelos;

import com.galaxite.client.render.EstadoDoRenderDaGem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModeloSafira extends EntityModel<EstadoDoRenderDaGem> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("galaxite", "safira"), "main");
	private final ModelPart body;
	private final ModelPart right_Leg;
	private final ModelPart left_Leg;
	private final ModelPart right_Arm;
	private final ModelPart left_Arm;
	private final ModelPart head;
	private final ModelPart composedHair;

	private final Map<String, ModelPart> gemParts = new HashMap<>();
	@SuppressWarnings("unused")
	private final ModelPart root;

	public ModeloSafira(ModelPart root) {
		super(root);
		this.root = root;
		this.body = root.getChild("body");
		this.right_Leg = this.body.getChild("right_Leg");
		this.left_Leg = this.body.getChild("left_Leg");
		this.right_Arm = this.body.getChild("right_Arm");
		this.left_Arm = this.body.getChild("left_Arm");
		this.head = root.getChild("head");
		this.composedHair = this.head.getChild("composedHair");

		initializeGemParts();
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		
		PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, -4.2F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 16).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(-6.0F, 2.775F, -4.5F, 12.0F, 9.0F, 10.0F, new CubeDeformation(0.276F))
				.texOffs(0, 44).addBox(-7.0F, 1.675F, -5.5F, 14.0F, 11.0F, 12.0F, new CubeDeformation(-0.95F))
				.texOffs(26, 16).addBox(-3.0F, 3.0F, -2.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		body.addOrReplaceChild("gem_Chest", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, -1.7306F, -2.4917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("gem_Navel", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 2.7694F, -5.1917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("gem_Pelvis", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 4.5194F, -5.1917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("gem_Upper_Back", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, -1.7306F, 0.5083F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("gem_Back", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 0.7694F, 0.5083F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("gem_Butt", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 2.5194F, 4.3583F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_Leg = body.addOrReplaceChild("right_Leg", CubeListBuilder.create().texOffs(52, 52).addBox(-1.0F, -1.3F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 61).addBox(-1.0F, -1.3F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(-1.5F, 6.3F, 0.0F));
		right_Leg.addOrReplaceChild("gem_Right_Thigh", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 1.7194F, -1.6917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_Leg.addOrReplaceChild("gem_Right_Knee", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 3.0194F, -0.5167F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_Leg.addOrReplaceChild("gem_Right_Calf", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 0.2194F, -0.3167F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_Leg = body.addOrReplaceChild("left_Leg", CubeListBuilder.create().texOffs(60, 52).addBox(-1.0F, -1.3F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 61).addBox(-1.0F, -1.3F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(1.5F, 6.3F, 0.0F));
		left_Leg.addOrReplaceChild("gem_Left_Thigh", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 1.7194F, -1.6917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_Leg.addOrReplaceChild("gem_Left_Knee", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 3.0194F, -0.5167F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_Leg.addOrReplaceChild("gem_Left_Calf", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, 0.2194F, -0.3167F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_Arm = body.addOrReplaceChild("right_Arm", CubeListBuilder.create().texOffs(46, 23).addBox(-1.0F, -1.2333F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(34, 29).addBox(-1.0F, 2.7667F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.001F))
				.texOffs(54, 19).addBox(-1.0F, -1.3333F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(-4.0F, -0.7667F, 0.0F, -1.0079F, 0.0F, 0.0F));
		right_Arm.addOrReplaceChild("gem_Right_Shoulder", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.0389F, -1.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_Arm.addOrReplaceChild("gem_Right_Arm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 1.2111F, -1.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_Arm.addOrReplaceChild("gem_Right_Hand", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, 3.3103F, -1.065F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_Arm.addOrReplaceChild("glove_r1", CubeListBuilder.create().texOffs(52, 42).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.9667F, 4.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition left_Arm = body.addOrReplaceChild("left_Arm", CubeListBuilder.create().texOffs(54, 23).addBox(-1.0F, -1.2333F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 29).addBox(-4.0F, 2.7667F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.001F))
				.texOffs(46, 19).addBox(-1.0F, -1.3333F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(4.0F, -0.7667F, 0.0F, -1.0079F, 0.0F, 0.0F));
		left_Arm.addOrReplaceChild("gem_Left_Shoulder", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.039F, -1.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_Arm.addOrReplaceChild("gem_Left_Arm", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 1.211F, -1.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_Arm.addOrReplaceChild("gem_Left_Hand", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 3.3103F, -1.065F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.8F, -5.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.8F, 1.5F));
		head.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(46, 34).addBox(-4.0F, -6.8F, -5.506F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Left_Eye", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, -4.7806F, -5.6917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.025F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Right_Eye", CubeListBuilder.create().texOffs(0, 0).addBox(-3.025F, -3.2083F, -5.0827F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.025F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Nape", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, -4.7806F, 1.3083F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Top_Head", CubeListBuilder.create().texOffs(0, 0).addBox(-0.975F, -8.8806F, -2.5417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Left_Ear", CubeListBuilder.create().texOffs(0, 0).addBox(2.875F, -3.7806F, -2.5917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Right_Ear", CubeListBuilder.create().texOffs(0, 0).addBox(-4.875F, -3.7806F, -2.5917F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Left_Cheek", CubeListBuilder.create().texOffs(0, 0).addBox(1.525F, -1.7806F, -6.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("gem_Right_Cheek", CubeListBuilder.create().texOffs(0, 0).addBox(-3.475F, -1.7806F, -6.0417F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition composedHair = head.addOrReplaceChild("composedHair", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0333F, -3.0964F, -5.8486F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.3F))
				.texOffs(71, 49).addBox(-10.0333F, -9.0964F, -1.4986F, 20.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0333F, -3.6036F, 0.3486F));
		composedHair.addOrReplaceChild("hair_top_piece_2_r1", CubeListBuilder.create().texOffs(60, 4).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1273F, -3.9464F, -1.538F, 0.0F, 2.3562F, 0.0F));
		composedHair.addOrReplaceChild("hair_top_piece_1_r1", CubeListBuilder.create().texOffs(60, 0).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.006F, -3.9464F, -1.538F, 0.0F, 0.7854F, 0.0F));
		composedHair.addOrReplaceChild("hair_long_lengh_r1", CubeListBuilder.create().texOffs(74, 18).addBox(-7.0F, -3.5F, -5.0F, 14.0F, 7.0F, 10.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(-0.0333F, 6.1818F, 4.0718F, -1.2697F, 0.0F, 0.0F));
		composedHair.addOrReplaceChild("hair_long_base_r1", CubeListBuilder.create().texOffs(64, 1).addBox(-5.0F, -5.0F, -4.5F, 10.0F, 8.0F, 9.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(-0.0333F, 0.0036F, 2.9514F, 1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 128, 78);
	}

	private void initializeGemParts() {
		gemParts.put("LEFT_EYE", head.getChild("gem_Left_Eye"));
		gemParts.put("RIGHT_EYE", head.getChild("gem_Right_Eye"));
		gemParts.put("CHEST", body.getChild("gem_Chest"));
		gemParts.put("NAVEL", body.getChild("gem_Navel"));
		gemParts.put("BACK", body.getChild("gem_Back"));
		gemParts.put("LEFT_HAND", left_Arm.getChild("gem_Left_Hand"));
		gemParts.put("RIGHT_HAND", right_Arm.getChild("gem_Right_Hand"));
	}

	private void hideAllGems() {
		for (ModelPart gemPart : gemParts.values()) {
			if (gemPart != null) gemPart.visible = false;
		}
		body.visible = true;
		head.visible = true;
		right_Arm.visible = true;
		left_Arm.visible = true;
		right_Leg.visible = true;
		left_Leg.visible = true;
		composedHair.visible = true;
	}

	@Override
	public void setupAnim(EstadoDoRenderDaGem state) {
		super.setupAnim(state);
		
		// 1. RECUPERAÇÃO DOS DADOS DO MOTOR GRÁFICO MODERNIZADO
		float limbSwing = state.walkAnimationPos;       
		float limbSwingAmount = state.walkAnimationSpeed; 
		float ageInTicks = state.ageInTicks;             

		// Multiplicador para converter Graus para Radianos (exigidos pelas dobras do modelo)
		float rad = 0.017453292F; 

		// 2. SISTEMA SEGURO DE RASTREAMENTO DO OLHAR (HEAD TRACKING)
		this.head.yRot = state.netHeadYaw * rad; // Aplica rotação lateral controlada
		this.head.xRot = state.headPitch * rad; // Aplica inclinação vertical controlada

		// 3. ANIMAÇÃO DE CAMINHADA BÍPEDE CRUSADA (ESTILO PLAYER)
		this.right_Leg.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_Leg.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.right_Arm.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.left_Arm.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

		this.right_Arm.zRot = 0.0F;
		this.left_Arm.zRot = 0.0F;

		// 4. ANIMAÇÃO DE RESPIRAÇÃO / PARADA (IDLE ANIMATION)
		this.right_Arm.zRot += net.minecraft.util.Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.left_Arm.zRot -= net.minecraft.util.Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.right_Arm.xRot += net.minecraft.util.Mth.sin(ageInTicks * 0.067F) * 0.05F;
		this.left_Arm.xRot -= net.minecraft.util.Mth.sin(ageInTicks * 0.067F) * 0.05F;

		// Balanço suave de "realeza" característico do cabelo longo e do corpo da Safira
		this.composedHair.zRot = net.minecraft.util.Mth.cos(ageInTicks * 0.03F) * 0.03F;
		this.body.zRot = net.minecraft.util.Mth.cos(ageInTicks * 0.03F) * 0.01F;

		// 5. GERENCIAMENTO DINÂMICO DA PEDRA
		hideAllGems();
		if (state.gemPlacement != null) {
			ModelPart activeGem = gemParts.get(state.gemPlacement.name());
			if (activeGem != null) activeGem.visible = true;
		}
	}
}