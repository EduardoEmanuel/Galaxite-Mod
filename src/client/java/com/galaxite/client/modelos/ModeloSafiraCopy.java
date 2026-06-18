package com.galaxite.client.modelos;

import java.util.HashMap;
import java.util.Map;

import com.galaxite.client.render.EstadoDoRenderDaGem;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;

public class ModeloSafiraCopy extends EntityModel<EstadoDoRenderDaGem> {
	
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("galaxite", "safira"), "main");

	private final ModelPart body;
	private final ModelPart gem_body;
	private final ModelPart right_Leg;
	private final ModelPart gem_right_leg;
	private final ModelPart left_Leg;
	private final ModelPart gem_left_knee;
	private final ModelPart normal_arms;
	private final ModelPart right_Arm;
	private final ModelPart gem_right_arm1;
	private final ModelPart left_Arm;
	private final ModelPart gem_left_arm1;
	private final ModelPart rebel_arms;
	private final ModelPart rebel_right_Arm;
	private final ModelPart gem_right_rebel_arm;
	private final ModelPart rebel_left_Arm;
	private final ModelPart gem_left_rebel_arm;
	private final ModelPart head;
	private final ModelPart composedHair;
	private final ModelPart gem_head;

	
	private final Map<String, ModelPart> gemParts = new HashMap<>();
	@SuppressWarnings("unused")
	private final ModelPart root;
	
	public ModeloSafiraCopy(ModelPart root) {
		super(root); 
		this.root = root;
		this.body = root.getChild("body");
		this.gem_body = this.body.getChild("gem_body");
		this.right_Leg = this.body.getChild("right_Leg");
		this.gem_right_leg = this.right_Leg.getChild("gem_right_leg");
		this.left_Leg = this.body.getChild("left_Leg");
		this.gem_left_knee = this.left_Leg.getChild("gem_left_knee");
		this.normal_arms = this.body.getChild("normal_arms");
		this.right_Arm = this.normal_arms.getChild("right_Arm");
		this.gem_right_arm1 = this.right_Arm.getChild("gem_right_arm1");
		this.left_Arm = this.normal_arms.getChild("left_Arm");
		this.gem_left_arm1 = this.left_Arm.getChild("gem_left_arm1");
		this.rebel_arms = this.body.getChild("rebel_arms");
		this.rebel_right_Arm = this.rebel_arms.getChild("rebel_right_Arm");
		this.gem_right_rebel_arm = this.rebel_right_Arm.getChild("gem_right_rebel_arm");
		this.rebel_left_Arm = this.rebel_arms.getChild("rebel_left_Arm");
		this.gem_left_rebel_arm = this.rebel_left_Arm.getChild("gem_left_rebel_arm");
		this.head = root.getChild("head");
		this.composedHair = this.head.getChild("composedHair");
		this.gem_head = this.head.getChild("gem_head");
		
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, 1.2F, -1.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 16).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-6.0F, -11.875F, -4.5F, 12.0F, 9.0F, 10.0F, new CubeDeformation(0.476F))
		.texOffs(0, 53).addBox(-7.0F, -12.775F, -5.5F, 14.0F, 11.0F, 12.0F, new CubeDeformation(-0.75F))
		.texOffs(10, 25).addBox(-3.0F, -6.0F, -2.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition gem_body = body.addOrReplaceChild("gem_body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.75F, -1.05F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -2.75F, -3.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -4.75F, -3.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, 1.75F, 1.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -0.75F, 1.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -2.5F, 5.8F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -3.0F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -3.0F, -1.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.025F, -2.0194F, -1.4417F));

		PartDefinition right_Leg = body.addOrReplaceChild("right_Leg", CubeListBuilder.create().texOffs(52, 61).addBox(-1.0F, -5.7F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 70).addBox(-1.0F, -2.7F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(-1.5F, -6.3F, 0.0F));

		PartDefinition gem_right_leg = right_Leg.addOrReplaceChild("gem_right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3F, -2.5F, -1.625F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.675F, -1.2194F, -0.0667F));

		PartDefinition left_Leg = body.addOrReplaceChild("left_Leg", CubeListBuilder.create().texOffs(60, 61).addBox(-1.0F, -5.7F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 70).addBox(-1.0F, -2.7F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(1.5F, -6.3F, 0.0F));

		PartDefinition gem_left_knee = left_Leg.addOrReplaceChild("gem_left_knee", CubeListBuilder.create().texOffs(0, 0).addBox(-1.65F, -2.5F, -1.625F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.675F, -1.2194F, -0.0667F));

		PartDefinition normal_arms = body.addOrReplaceChild("normal_arms", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.7667F, 0.0F));

		PartDefinition right_Arm = normal_arms.addOrReplaceChild("right_Arm", CubeListBuilder.create().texOffs(44, 20).addBox(-1.0F, -4.7667F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 26).addBox(-1.0F, -4.7667F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.001F))
		.texOffs(52, 16).addBox(-1.0F, -0.6667F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0079F, 0.0F, 0.0F));

		PartDefinition glove_r1 = right_Arm.addOrReplaceChild("glove_r1", CubeListBuilder.create().texOffs(48, 30).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.9667F, -4.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition gem_right_arm1 = right_Arm.addOrReplaceChild("gem_right_arm1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.35F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(3.15F, -6.1132F, -1.0519F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-0.35F, -1.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.15F, 0.2889F, -0.0417F));

		PartDefinition left_Arm = normal_arms.addOrReplaceChild("left_Arm", CubeListBuilder.create().texOffs(52, 20).addBox(-1.0F, -4.7667F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 26).addBox(-4.0F, -4.7667F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.001F))
		.texOffs(44, 16).addBox(-1.0F, -0.6667F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, -1.0079F, 0.0F, 0.0F));

		PartDefinition gem_left_arm1 = left_Arm.addOrReplaceChild("gem_left_arm1", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, 0.55F, -0.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(2.5F, 2.8F, -0.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(3.2F, 3.05F, -0.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -2.0632F, -0.2519F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-3.0F, -3.761F, -0.8417F));

		PartDefinition rebel_arms = body.addOrReplaceChild("rebel_arms", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 0.7667F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition rebel_right_Arm = rebel_arms.addOrReplaceChild("rebel_right_Arm", CubeListBuilder.create().texOffs(52, 20).addBox(-1.0F, -6.7667F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 16).addBox(-1.0F, -0.6667F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F))
		.texOffs(64, 30).addBox(-1.0333F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0079F, 0.0F, 0.0F));

		PartDefinition gem_right_rebel_arm = rebel_right_Arm.addOrReplaceChild("gem_right_rebel_arm", CubeListBuilder.create().texOffs(0, 0).addBox(-0.45F, -3.5F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-0.35F, -3.5F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(0.15F, -7.8492F, -1.0232F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 0).addBox(0.15F, -8.2992F, -1.0232F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 0).addBox(-0.6F, -7.1132F, -1.0519F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-0.35F, -1.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.15F, 0.2889F, -0.0417F));

		PartDefinition rebel_left_Arm = rebel_arms.addOrReplaceChild("rebel_left_Arm", CubeListBuilder.create().texOffs(44, 20).addBox(-1.0F, -6.7667F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 16).addBox(-1.0F, -0.6667F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.65F))
		.texOffs(56, 30).addBox(-1.0333F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, -1.0079F, 0.0F, 0.0F));

		PartDefinition gem_left_rebel_arm = rebel_left_Arm.addOrReplaceChild("gem_left_rebel_arm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.7F, -3.5F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-2.2F, -7.8492F, -1.0232F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 0).addBox(-1.7F, -1.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.65F, -3.5F, -0.95F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-2.2F, -8.2992F, -1.0232F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 0).addBox(-1.45F, -7.1132F, -1.0519F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.2F, 0.289F, -0.0417F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 43).addBox(-4.0F, -2.2F, -5.506F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(0, 0).addBox(-4.0F, -1.2F, -5.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.2F, 1.5F));

		PartDefinition composedHair = head.addOrReplaceChild("composedHair", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0333F, -4.9036F, -5.8486F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.35F))
		.texOffs(76, 34).addBox(-10.0333F, -4.9036F, -1.4986F, 20.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0333F, 3.6036F, 0.3486F));

		PartDefinition hair_long_base_detail_r1 = composedHair.addOrReplaceChild("hair_long_base_detail_r1", CubeListBuilder.create().texOffs(72, 48).addBox(-12.0F, -9.0F, 0.0F, 24.0F, 24.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0333F, -4.9036F, 3.5014F, 0.2618F, 0.0F, 0.0F));

		PartDefinition hair_long_base_top_piece_3_r1 = composedHair.addOrReplaceChild("hair_long_base_top_piece_3_r1", CubeListBuilder.create().texOffs(96, 4).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0273F, 5.9464F, 2.012F, 0.0F, 2.3562F, 0.0F));

		PartDefinition hair_long_base_top_piece_2_r1 = composedHair.addOrReplaceChild("hair_long_base_top_piece_2_r1", CubeListBuilder.create().texOffs(96, 0).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.094F, 5.9464F, 2.012F, 0.0F, 0.7854F, 0.0F));

		PartDefinition hair_top_piece_2_r1 = composedHair.addOrReplaceChild("hair_top_piece_2_r1", CubeListBuilder.create().texOffs(60, 4).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1273F, 3.9464F, -1.538F, 0.0F, 2.3562F, 0.0F));

		PartDefinition hair_top_piece_1_r1 = composedHair.addOrReplaceChild("hair_top_piece_1_r1", CubeListBuilder.create().texOffs(60, 0).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.006F, 3.9464F, -1.538F, 0.0F, 0.7854F, 0.0F));

		PartDefinition hair_long_lengh_r1 = composedHair.addOrReplaceChild("hair_long_lengh_r1", CubeListBuilder.create().texOffs(74, 17).addBox(-7.0F, -3.5F, -5.0F, 14.0F, 7.0F, 10.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(-0.0333F, -6.1818F, 4.0718F, -1.2697F, 0.0F, 0.0F));

		PartDefinition hair_long_base_r1 = composedHair.addOrReplaceChild("hair_long_base_r1", CubeListBuilder.create().texOffs(64, 0).addBox(-5.0F, -3.0F, -4.5F, 10.0F, 8.0F, 9.0F, new CubeDeformation(0.35F)), PartPose.offsetAndRotation(-0.0333F, -0.0036F, 2.9514F, 1.5708F, 0.0F, 0.0F));

		PartDefinition gem_head = head.addOrReplaceChild("gem_head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 1.6F, -1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.03F))
		.texOffs(0, 0).addBox(-3.5F, 3.6F, -1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.03F))
		.texOffs(0, 0).addBox(-3.5F, 2.5F, 6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-3.5F, 5.3F, 2.15F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-7.4F, 1.5F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(0.35F, 1.5F, 2.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-8.5F, 1.5F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(1.45F, 1.5F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-6.0F, -1.0F, -1.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -1.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(2.525F, 0.2806F, -4.6917F));

		PartDefinition gem_top_head_hair_long_base_r1 = gem_head.addOrReplaceChild("gem_top_head_hair_long_base_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.5F, 7.6F, 3.15F, -0.7854F, 0.0F, 0.0F));

		PartDefinition gem_hair_r1 = gem_head.addOrReplaceChild("gem_hair_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.5F, 6.3F, -0.55F, -0.7854F, 0.0F, 0.0F));

		PartDefinition gem_nape_hair_long_base_r1 = gem_head.addOrReplaceChild("gem_nape_hair_long_base_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.5F, 3.5F, 11.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 128, 78);
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
	}

}