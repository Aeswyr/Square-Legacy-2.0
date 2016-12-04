package com.IB.SL.graphics;

import java.awt.Color;


public class Sprite {
	
	/**
	 * 
	 */
	// ACROSS THEN DOWN KID
	//Down the pink space is 16!
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	//MiniMap(s)
	public static Sprite MiniMap = new Sprite(32 , 0, 0, SpriteSheet.minimap);
	//GUIS
	public static Sprite Title = new Sprite(300, 0, 0, SpriteSheet.title);
	public static Sprite title_overContinue = new Sprite(300, 0, 0, SpriteSheet.title_OverContinue);
	public static Sprite title_Chars = new Sprite(300, 0, 0, SpriteSheet.title_Char);
	public static Sprite title_NewChar = new Sprite(300, 0, 0, SpriteSheet.title_NewChar);
	public static Sprite title_overChars = new Sprite(300, 0, 0, SpriteSheet.title_OverChars);
	public static Sprite title_overTrash = new Sprite(22, 20, 91, 198, SpriteSheet.title_Char);

	public static Sprite pauseMenu = new Sprite(300, 0, 0, SpriteSheet.pauseMenu);
	public static Sprite pauseMenuResume = new Sprite(300, 0, 0, SpriteSheet.pauseMenuResume);
	public static Sprite pauseMenuMenu = new Sprite(300, 0, 0, SpriteSheet.pauseMenuMenu);
	public static Sprite pauseMenuQuit = new Sprite(300, 0, 0, SpriteSheet.pauseMenuQuit);
	public static Sprite AB_LOCK = new Sprite(20, 0, 0, (new SpriteSheet("/Textures/sheets/01_GUI/AB_LOCK.png", 20, 20)));
	public static Sprite pauseOptions = new Sprite(300, 0, 0, SpriteSheet.options);

	
	public static Sprite invAbilityGreen = new Sprite(160, 0, 0, SpriteSheet.invAbilityGreen);


	public static Sprite robobob = new Sprite(20, 0, 0, SpriteSheet.robobob);
	
	public static Sprite water = new Sprite(16, 0, 0, SpriteSheet.Water_Main);
	public static Sprite water2 = new Sprite(16, 0, 1, SpriteSheet.Water_Main);
	//public static Sprite ibLOGO = new Sprite(300, 0, 0, SpriteSheet.ibLOGO);
	//public static Sprite HealthBarExperimental = new Sprite(106, 0, 0, SpriteSheet.healthBarExperimental);

	public static Sprite HealthBar0 = new Sprite(32, 0, 20, SpriteSheet.healthbar);
	public static Sprite HealthBar1 = new Sprite(32, 0, 19, SpriteSheet.healthbar);
	public static Sprite HealthBar2 = new Sprite(32, 0, 18, SpriteSheet.healthbar);
	public static Sprite HealthBar3 = new Sprite(32, 0, 17, SpriteSheet.healthbar);
	public static Sprite HealthBar4 = new Sprite(32, 0, 16, SpriteSheet.healthbar);
	public static Sprite HealthBar5 = new Sprite(32, 0, 15, SpriteSheet.healthbar);
	public static Sprite HealthBar6 = new Sprite(32, 0, 14, SpriteSheet.healthbar);
	public static Sprite HealthBar7 = new Sprite(32, 0, 13, SpriteSheet.healthbar);
	public static Sprite HealthBar8 = new Sprite(32, 0, 12, SpriteSheet.healthbar);
	public static Sprite HealthBar9 = new Sprite(32, 0, 11, SpriteSheet.healthbar);
	public static Sprite HealthBar10 = new Sprite(32, 0, 10, SpriteSheet.healthbar);
	public static Sprite HealthBar11 = new Sprite(32, 0, 9, SpriteSheet.healthbar);
	public static Sprite HealthBar12 = new Sprite(32, 0, 8, SpriteSheet.healthbar);
	public static Sprite HealthBar13 = new Sprite(32, 0, 7, SpriteSheet.healthbar);
	public static Sprite HealthBar14 = new Sprite(32, 0, 6, SpriteSheet.healthbar);
	public static Sprite HealthBar15 = new Sprite(32, 0, 5, SpriteSheet.healthbar);
	public static Sprite HealthBar16 = new Sprite(32, 0, 4, SpriteSheet.healthbar);
	public static Sprite HealthBar17 = new Sprite(32, 0, 3, SpriteSheet.healthbar);
	public static Sprite HealthBar18 = new Sprite(32, 0, 2, SpriteSheet.healthbar);
	public static Sprite HealthBar19 = new Sprite(32, 0, 1, SpriteSheet.healthbar);
	public static Sprite HealthBar20 = new Sprite(32, 0, 0, SpriteSheet.healthbar);
	
	public static Sprite MobHealthBar0 = new Sprite(32, 0, 20, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar1 = new Sprite(32, 0, 19, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar2 = new Sprite(32, 0, 18, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar3 = new Sprite(32, 0, 17, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar4 = new Sprite(32, 0, 16, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar5 = new Sprite(32, 0, 15, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar6 = new Sprite(32, 0, 14, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar7 = new Sprite(32, 0, 13, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar8 = new Sprite(32, 0, 12, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar9 = new Sprite(32, 0, 11, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar10 = new Sprite(32, 0, 10, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar11 = new Sprite(32, 0, 9, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar12 = new Sprite(32, 0, 8, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar13 = new Sprite(32, 0, 7, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar14 = new Sprite(32, 0, 6, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar15 = new Sprite(32, 0, 5, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar16 = new Sprite(32, 0, 4, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar17 = new Sprite(32, 0, 3, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar18 = new Sprite(32, 0, 2, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar19 = new Sprite(32, 0, 1, SpriteSheet.mobHealth);
	public static Sprite MobHealthBar20 = new Sprite(32, 0, 0, SpriteSheet.mobHealth);
	
	public static Sprite manabar0 = new Sprite(32, 0, 20, SpriteSheet.manabar);
	public static Sprite manabar1 = new Sprite(32, 0, 19, SpriteSheet.manabar);
	public static Sprite manabar2 = new Sprite(32, 0, 18, SpriteSheet.manabar);
	public static Sprite manabar3 = new Sprite(32, 0, 17, SpriteSheet.manabar);
	public static Sprite manabar4 = new Sprite(32, 0, 16, SpriteSheet.manabar);
	public static Sprite manabar5 = new Sprite(32, 0, 15, SpriteSheet.manabar);
	public static Sprite manabar6 = new Sprite(32, 0, 14, SpriteSheet.manabar);
	public static Sprite manabar7 = new Sprite(32, 0, 13, SpriteSheet.manabar);
	public static Sprite manabar8 = new Sprite(32, 0, 12, SpriteSheet.manabar);
	public static Sprite manabar9 = new Sprite(32, 0, 11, SpriteSheet.manabar);
	public static Sprite manabar10 = new Sprite(32, 0, 10, SpriteSheet.manabar);
	public static Sprite manabar11 = new Sprite(32, 0, 9, SpriteSheet.manabar);
	public static Sprite manabar12 = new Sprite(32, 0, 8, SpriteSheet.manabar);
	public static Sprite manabar13 = new Sprite(32, 0, 7, SpriteSheet.manabar);
	public static Sprite manabar14 = new Sprite(32, 0, 6, SpriteSheet.manabar);
	public static Sprite manabar15 = new Sprite(32, 0, 5, SpriteSheet.manabar);
	public static Sprite manabar16 = new Sprite(32, 0, 4, SpriteSheet.manabar);
	public static Sprite manabar17 = new Sprite(32, 0, 3, SpriteSheet.manabar);
	public static Sprite manabar18 = new Sprite(32, 0, 2, SpriteSheet.manabar);
	public static Sprite manabar19 = new Sprite(32, 0, 1, SpriteSheet.manabar);
	public static Sprite manabar20 = new Sprite(32, 0, 0, SpriteSheet.manabar);
	
	public static Sprite StaminaBar0 = new Sprite(32, 0, 20, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar1 = new Sprite(32, 0, 19, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar2 = new Sprite(32, 0, 18, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar3 = new Sprite(32, 0, 17, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar4 = new Sprite(32, 0, 16, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar5 = new Sprite(32, 0, 15, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar6 = new Sprite(32, 0, 14, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar7 = new Sprite(32, 0, 13, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar8 = new Sprite(32, 0, 12, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar9 = new Sprite(32, 0, 11, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar10 = new Sprite(32, 0, 10, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar11 = new Sprite(32, 0, 9, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar12 = new Sprite(32, 0, 8, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar13 = new Sprite(32, 0, 7, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar14 = new Sprite(32, 0, 6, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar15 = new Sprite(32, 0, 5, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar16 = new Sprite(32, 0, 4, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar17 = new Sprite(32, 0, 3, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar18 = new Sprite(32, 0, 2, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar19 = new Sprite(32, 0, 1, SpriteSheet.StaminaBar);
	public static Sprite StaminaBar20 = new Sprite(32, 0, 0, SpriteSheet.StaminaBar);

	public static Sprite button_Default = new Sprite(108, 0, 0, SpriteSheet.buttonSet);
	public static Sprite button_Push = new Sprite(108, 0, 1, SpriteSheet.buttonSet);

	public static Sprite slimeyParticle = new Sprite(3, 0xAAAAAA);

	public static Sprite selectedAbility = new Sprite(20, 0, 0, SpriteSheet.abilitybox);
	public static Sprite abilitybox = new Sprite(18, 0, 0, SpriteSheet.abilitybox2);

	public static Sprite AbilityBarOpen = new Sprite(32, 0, 0, SpriteSheet.AbilityBarOpen); // set 32 to 300 to restore full boxes
	public static Sprite AbilityBarClosed = new Sprite(32, 0, 0, SpriteSheet.AbilityBarClosed);
	
	//public static Sprite HealthPotion2 = new Sprite(8, 0, 0, SpriteSheet.HealthPotion);

	public static Sprite ManaPotion = new Sprite(16, 2, 0, SpriteSheet.Items);
	public static Sprite HealthPotion = new Sprite(16, 3, 0, SpriteSheet.Items);
	public static Sprite StaminaPotion = new Sprite(16, 4, 0, SpriteSheet.Items);
	public static Sprite InvisPotion = new Sprite(16, 10, 0, SpriteSheet.Items);

	
	public static Sprite GoldenCoin = new Sprite(16, 6, 0, SpriteSheet.Items);
	public static Sprite NetherRecrement = new Sprite(16, 6, 1, SpriteSheet.Items);

	public static Sprite Ticket = new Sprite(16, 7, 0, SpriteSheet.Items);

	public static Sprite stick = new Sprite(16, 9, 0, SpriteSheet.Items);
	public static Sprite BindingOoze = new Sprite(16, 9, 1, SpriteSheet.Items);
	public static Sprite EtherialBindingOoze = new Sprite(16, 9, 2, SpriteSheet.Items);
	public static Sprite AbyssalBindingOoze = new Sprite(16, 8, 3, SpriteSheet.Items);
	public static Sprite LeatherSwatch = new Sprite(16, 10, 1, SpriteSheet.Items);
	public static Sprite IronIngot = new Sprite(16, 11, 0, SpriteSheet.Items);
	public static Sprite SteelIngot = new Sprite(16, 12, 0, SpriteSheet.Items);
	public static Sprite CopperIngot = new Sprite(16, 11, 1, SpriteSheet.Items);
	public static Sprite FlameEsscence = new Sprite(16, 14, 0, SpriteSheet.Items);
	public static Sprite EarthEsscence = new Sprite(16, 15, 1, SpriteSheet.Items);
	public static Sprite ThunderEsscence = new Sprite(16, 14, 1, SpriteSheet.Items);
	public static Sprite WaterEsscence = new Sprite(16, 15, 0, SpriteSheet.Items);
	public static Sprite VoidEsscence = new Sprite(16, 14, 2, SpriteSheet.Items);
	public static Sprite ArcaneEsscence = new Sprite(16, 15, 2, SpriteSheet.Items);
	public static Sprite ArcanemIngot = new Sprite(16, 13, 0, SpriteSheet.Items);
	public static Sprite ThreadSpool = new Sprite(16, 8, 2, SpriteSheet.Items);

	public static Sprite OpticBond = new Sprite(16, 1, 0, SpriteSheet.Equipment_Util);
	public static Sprite BrokenHilt = new Sprite(16, 7, 2, SpriteSheet.Equipment_Util);
	public static Sprite EmptyFlask = new Sprite(16, 10, 2, SpriteSheet.Equipment_Util);
	public static Sprite SmallStone = new Sprite(16, 9, 2, SpriteSheet.Equipment_Util);
	public static Sprite SilverCross = new Sprite(16, 8, 2, SpriteSheet.Equipment_Util);
	public static Sprite TatteredCloth = new Sprite(16, 11, 2, SpriteSheet.Equipment_Util);
	public static Sprite ObsidianShard = new Sprite(16, 11, 0, SpriteSheet.Equipment_Util);
	public static Sprite DiamondShard = new Sprite(16, 5, 0, SpriteSheet.Equipment_Util);
	public static Sprite CubeOfBefuddlement = new Sprite(16, 7, 0, SpriteSheet.Equipment_Util);
	public static Sprite Pentagram = new Sprite(16, 6, 1, SpriteSheet.Equipment_Util);
	public static Sprite FrostflamePentagram = new Sprite(16, 7, 1, SpriteSheet.Equipment_Util);
	public static Sprite ShardOfTrueIce = new Sprite(16, 6, 2, SpriteSheet.Equipment_Util);
	public static Sprite Charm_VIT = new Sprite(16, 12, 0, SpriteSheet.Equipment_Util);
	public static Sprite Charm_WIS = new Sprite(16, 13, 0, SpriteSheet.Equipment_Util);
	public static Sprite Charm_EDR = new Sprite(16, 14, 0, SpriteSheet.Equipment_Util);
	public static Sprite Charm_ATC = new Sprite(16, 15, 0, SpriteSheet.Equipment_Util);
	public static Sprite Charm_MAT = new Sprite(16, 15, 1, SpriteSheet.Equipment_Util);
	public static Sprite Charm_MDF = new Sprite(16, 14, 1, SpriteSheet.Equipment_Util);
	public static Sprite Charm_DEF = new Sprite(16, 13, 1, SpriteSheet.Equipment_Util);
	public static Sprite Charm_AGI = new Sprite(16, 12, 1, SpriteSheet.Equipment_Util);
	public static Sprite ring_Precious = new Sprite(16, 4, 1, SpriteSheet.Equipment_Util);
	public static Sprite ring_MinorHealth = new Sprite(16, 9, 0, SpriteSheet.Equipment_Util);
	public static Sprite ring_MajorHealth = new Sprite(16, 9, 1, SpriteSheet.Equipment_Util);
	public static Sprite ring_MajorStamina = new Sprite(16, 10, 1, SpriteSheet.Equipment_Util);
	public static Sprite ring_MajorMana = new Sprite(16, 8, 1, SpriteSheet.Equipment_Util);
	public static Sprite ring_MinorMana = new Sprite(16, 8, 0, SpriteSheet.Equipment_Util);
	public static Sprite ring_MinorStamina = new Sprite(16, 10, 0, SpriteSheet.Equipment_Util);
	
	
	public static Sprite chest_CottonRobe = new Sprite(16, 0, 2, SpriteSheet.Equipment_Main);
	public static Sprite chest_LinenRobes = new Sprite(16, 2, 2, SpriteSheet.Equipment_Main);
	public static Sprite chest_SunAcolyteCloak = new Sprite(16, 4, 2, SpriteSheet.Equipment_Main);
	public static Sprite chest_MaelstromRobes = new Sprite(16, 8, 2, SpriteSheet.Equipment_Main);
	public static Sprite chest_CopperArmor = new Sprite(16, 1, 0, SpriteSheet.Equipment_Main);
	public static Sprite chest_IronArmor = new Sprite(16, 0, 0, SpriteSheet.Equipment_Main);
	public static Sprite chest_DragonArmor = new Sprite(16, 2, 0, SpriteSheet.Equipment_Main);
	public static Sprite chest_AbyssalArmor = new Sprite(16, 8, 0, SpriteSheet.Equipment_Main);
	public static Sprite chest_LeatherTunic = new Sprite(16, 1, 4, SpriteSheet.Equipment_Main);
	public static Sprite chest_StuddedLeatherTunic = new Sprite(16, 4, 4, SpriteSheet.Equipment_Main);
	public static Sprite chest_WyvernSkinArmor = new Sprite(16, 2, 4, SpriteSheet.Equipment_Main);
	public static Sprite chest_ChoasTunic = new Sprite(16, 8, 4, SpriteSheet.Equipment_Main);

	
	public static Sprite button_newPoints = new Sprite(16, 5, 0, SpriteSheet.Buttons);
	public static Sprite button_Over_newPoints = new Sprite(16, 6, 0, SpriteSheet.Buttons);

	//Structures:
	public static Sprite Teleporter1 = new Sprite(64, 0 , 0, SpriteSheet.Teleporter);
	public static Sprite Chest_Dungeon_C = new Sprite(16, 17, 17, SpriteSheet.blocks);
	public static Sprite Chest_Dungeon_O = new Sprite(16, 18, 17, SpriteSheet.blocks);

	


	public static Sprite bone = new Sprite(16, 24, 19, SpriteSheet.blocks);
	public static Sprite crossBone = new Sprite(16, 23, 20, SpriteSheet.blocks);
	public static Sprite skull = new Sprite(16, 24, 20, SpriteSheet.blocks);
	
	public static Sprite swordInDirt = new Sprite(16, 18, 16, SpriteSheet.blocks);
	
	public static Sprite Carraige = new Sprite(128, 32, 0, 0, SpriteSheet.Carraige);

	
	//Staves:
	
	public static Sprite wand_ArcaneTwig = new Sprite(16, 7, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_FlareScepter = new Sprite(16, 2, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_Pulsefire = new Sprite(16, 3, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_VoidCrook = new Sprite(16, 4, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_StygianScepter = new Sprite(16, 8, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_ContradictionWand = new Sprite(16, 10, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_Crystalline = new Sprite(16, 0, 8, SpriteSheet.Equipment_Main);
	public static Sprite wand_BlastingRod = new Sprite(16, 1, 8, SpriteSheet.Equipment_Main);

	
	//Projectiles:
	
	public static Sprite HealingSpell = new Sprite(16, 0, 1, SpriteSheet.spells_Display);
	public static Sprite RadialBlast = new Sprite(16, 3, 7, SpriteSheet.spells);
	public static Sprite RadialBlast_Display = new Sprite(16, 4, 0, SpriteSheet.spells_Display);
	public static Sprite Searing_Bolt_Display = new Sprite(16, 4, 1, SpriteSheet.spells_Display);
	public static Sprite WElemental_Display = new Sprite(16, 9, 1, SpriteSheet.spells_Display);
	public static Sprite GoldenOrb = new Sprite(16, 2, 0, SpriteSheet.spells);
	public static Sprite Searing_Bolt = new Sprite(16, 5, 0, SpriteSheet.spells);
	public static Sprite SlimeBallProj = new Sprite(16, 0, 4, SpriteSheet.spells);
	public static Sprite WizardProjectile = new Sprite(16, 0, 0, SpriteSheet.spells);
	public static Sprite RockTHROWING = new Sprite(16, 3, 2, SpriteSheet.spells);
	public static Sprite fireParticle = new Sprite(16, 0, 0, SpriteSheet.particles);
	public static Sprite WizardProjectile2 = new Sprite(16, 0, 3, SpriteSheet.spells);
	public static Sprite Blink_Display = new Sprite(16, 0, 3, SpriteSheet.spells_Display);
	public static Sprite Equil_Display = new Sprite(16, 0, 2, SpriteSheet.spells_Display);
	public static Sprite SiphonSucc_Display = new Sprite(16, 0, 0, SpriteSheet.Siphon_down);
	public static Sprite OpticalProjectile = new Sprite(16, 2, 9, SpriteSheet.spells);
	public static Sprite RedShockwave = new Sprite(16, 4, 8, SpriteSheet.spells);
	public static Sprite Pulsefire = new Sprite(16, 0, 13, SpriteSheet.spells);
	public static Sprite Flare = new Sprite(16, 0, 14, SpriteSheet.spells);
	public static Sprite VoidCrook = new Sprite(16, 0, 15, SpriteSheet.spells);
	public static Sprite Stygian = new Sprite(16, 0, 3, SpriteSheet.spells);
	public static Sprite Contradiction = new Sprite(16, 0, 8, SpriteSheet.spells);
	public static Sprite PoisonGoo = new Sprite(16, 6, 1, SpriteSheet.spells);
	public static Sprite DemonOrb = new Sprite(16, 5, 2, SpriteSheet.spells);
	
	public static Sprite NullWave = new Sprite(32, 0, 0, SpriteSheet.ability_NullBoss);
	public static Sprite NullBolt = new Sprite(32, 0, 1, SpriteSheet.ability_NullBoss);
	
	
	public static Sprite display_Poison = new Sprite(16, 2, 0, SpriteSheet.Buttons);
	public static Sprite display_Frozen = new Sprite(16, 7, 0, SpriteSheet.Buttons);
	
	public static Sprite ArrowRight = new Sprite(16, 6, 1, SpriteSheet.Buttons);
	public static Sprite ArrowLeft = new Sprite(16, 5, 1, SpriteSheet.Buttons);

	//Particles:
	public static Sprite particle_def = new Sprite(2, 0x7F0000);
	//public static Sprite wallparticle = new Sprite(1, 0xAAAAAA);
	public static Sprite wallparticle = new Sprite(1, 0x0000BF);
	public static Sprite VoidParticle = new Sprite(3, 0x050525);
	
	public static Sprite bleed = new Sprite(1, 0x7F0000);
	public static Sprite Rock = new Sprite(3, 0x808080);

	//static Color Opaque = new Color(5, 0, 0, 120);

	//public static Sprite staticGrey = new Sprite(99, 59, Opaque);
	
	
	//BLOCKS: 0.4 SOMETHINGS ARE NOT NAMED CORRECTLY DOUBLE CHECK TMO
	
	
	//Blocks:
	
	public static Sprite Torch = new Sprite(16, 20, 20, SpriteSheet.blocks);
	
	//public static Sprite Torchr = rotate(Torch);
	

	//public static Sprite Torch = resize(Torch, 0.4);
	
	//New Blocks Update Beta 2.0
	public static Sprite Occulus = new Sprite(32, 0, 0, SpriteSheet.Occulus);

	
	public static Sprite playerback = new Sprite(16, 0, 0, SpriteSheet.player_downstill);
	public static Sprite playerup = new Sprite(16, 0, 0, SpriteSheet.player_upstill);
	public static Sprite playerleft = new Sprite(16, 0, 0, SpriteSheet.player_leftstill);
	public static Sprite playerright = new Sprite(16, 0, 0, SpriteSheet.player_rightstill);

	
	public static Sprite Swirly = new Sprite(16, 12, 1, SpriteSheet.blocks);
	public static Sprite bitBrick = new Sprite(16, 2, 2, SpriteSheet.blocks);
	public static Sprite bitMetal = new Sprite(16, 3, 2, SpriteSheet.blocks);
	public static Sprite Bluefog = new Sprite(16, 11, 1, SpriteSheet.blocks);
	public static Sprite BookshelfBottom = new Sprite(16, 11, 17, SpriteSheet.blocks);
	public static Sprite BookshelfTop = new Sprite(16, 11, 16, SpriteSheet.blocks);
	public static Sprite BrickCeiling = new Sprite(16, 6, 16, SpriteSheet.blocks);
	public static Sprite BrickWall = new Sprite(16, 6, 17, SpriteSheet.blocks);
	public static Sprite CaveCeiling = new Sprite(16, 14, 16, SpriteSheet.blocks);
	public static Sprite CaveWall = new Sprite(16, 14, 17, SpriteSheet.blocks);
	public static Sprite CobbleStone = new Sprite(16, 2, 0, SpriteSheet.blocks);
	public static Sprite CobblestoneCeiling = new Sprite(16, 5, 16, SpriteSheet.blocks);
	public static Sprite CobbleStoneWall = new Sprite(16, 5, 17, SpriteSheet.blocks);
	public static Sprite ColorFlowers = new Sprite(16, 4, 1, SpriteSheet.blocks);
	public static Sprite CrackedBrick = new Sprite(16, 10, 1, SpriteSheet.blocks);
	public static Sprite DarkStone = new Sprite(16, 3, 1, SpriteSheet.blocks);
	public static Sprite DeepLava = new Sprite(16, 5, 1, SpriteSheet.blocks);
	public static Sprite DeepWater = new Sprite(16, 11, 0, SpriteSheet.blocks);
	public static Sprite Dirt = new Sprite(16, 15, 1, SpriteSheet.blocks);
	public static Sprite DirtCeiling = new Sprite(16, 0, 16, SpriteSheet.blocks);
	public static Sprite DirtWall = new Sprite(16, 0, 17, SpriteSheet.blocks);
	public static Sprite DresserBottom = new Sprite(16, 12, 17, SpriteSheet.blocks);
	public static Sprite DresserTop = new Sprite(16, 12, 16, SpriteSheet.blocks);
	public static Sprite Grass = new Sprite(16, 0, 0, SpriteSheet.blocks);
	public static Sprite HellBrick = new Sprite(16, 10, 0, SpriteSheet.blocks);
	public static Sprite HellBrickCeiling = new Sprite(16, 2, 16, SpriteSheet.blocks);
	public static Sprite HellbrickWall = new Sprite(16, 2, 17, SpriteSheet.blocks);
	public static Sprite HellCaveCeiling = new Sprite(16, 0, 18, SpriteSheet.blocks);
	public static Sprite HellCaveWall = new Sprite(16, 0, 19, SpriteSheet.blocks);
	public static Sprite Hellsand = new Sprite(16, 6, 0, SpriteSheet.blocks);
	public static Sprite HellSandCeiling = new Sprite(16, 2, 18, SpriteSheet.blocks);
	public static Sprite HellSandWall = new Sprite(16, 2, 19, SpriteSheet.blocks);
	public static Sprite Hellstone = new Sprite(16, 1, 0, SpriteSheet.blocks);
	public static Sprite Ice = new Sprite(16, 0, 2, SpriteSheet.blocks);
	public static Sprite IceBrick = new Sprite(16, 8, 0, SpriteSheet.blocks);
	public static Sprite IceBrickCeiling = new Sprite(16, 3, 16, SpriteSheet.blocks);
	public static Sprite IceBrickWall = new Sprite(16, 3, 17, SpriteSheet.blocks);
	public static Sprite IceCaveCeiling = new Sprite(16, 10, 16, SpriteSheet.blocks);
	public static Sprite IceCaveWall = new Sprite(16, 10, 17, SpriteSheet.blocks);
	public static Sprite IceSand = new Sprite(16, 5, 0, SpriteSheet.blocks);
	public static Sprite IceSandCeiling = new Sprite(16, 1, 18, SpriteSheet.blocks);
	public static Sprite IceSandWall = new Sprite(16, 1, 19, SpriteSheet.blocks);
	public static Sprite Lava = new Sprite(16, 13, 0, SpriteSheet.blocks);
	public static Sprite Metal = new Sprite(16, 0, 1, SpriteSheet.blocks);
	public static Sprite MetalCeiling = new Sprite(16, 13, 16, SpriteSheet.blocks);
	public static Sprite MetalWall = new Sprite(16, 13, 17, SpriteSheet.blocks);
	public static Sprite MossCeiling = new Sprite(16, 7, 16, SpriteSheet.blocks);
	public static Sprite MossWall = new Sprite(16, 7, 17, SpriteSheet.blocks);
	public static Sprite ObsidianCeiling = new Sprite(16, 9, 16, SpriteSheet.blocks);
	public static Sprite ObsidianWall = new Sprite(16, 9, 17, SpriteSheet.blocks);
	public static Sprite PathDirt = new Sprite(16, 15, 0, SpriteSheet.blocks);
	public static Sprite Sand = new Sprite(16, 9, 1, SpriteSheet.blocks);
	public static Sprite SandBrick = new Sprite(16, 9, 0, SpriteSheet.blocks);
	public static Sprite SandBrickCeiling = new Sprite(16, 1, 16, SpriteSheet.blocks);
	public static Sprite SandBrickWall = new Sprite(16, 1, 17, SpriteSheet.blocks);
	public static Sprite SandCeiling = new Sprite(16, 15, 16, SpriteSheet.blocks);
	public static Sprite SandWall = new Sprite(16, 15, 17, SpriteSheet.blocks);
	public static Sprite Snow = new Sprite(16, 2, 1, SpriteSheet.blocks);
	public static Sprite StoneBrick = new Sprite(16, 7, 0, SpriteSheet.blocks);
	public static Sprite StoneBrickCeiling = new Sprite(16, 8, 16, SpriteSheet.blocks);
	public static Sprite StoneBrickWall = new Sprite(16, 8, 17, SpriteSheet.blocks);
	public static Sprite Swamp = new Sprite(16, 8, 1, SpriteSheet.blocks);
	public static Sprite Water = new Sprite(16, 13, 1, SpriteSheet.blocks);
	//public static Sprite VoidTile = new Sprite(16, 12, 1, SpriteSheet.blocks);
	public static Sprite VoidTile = Water;
	public static Sprite Wood = new Sprite(16, 1, 1, SpriteSheet.blocks);
	public static Sprite WoodCeiling = new Sprite(16, 4, 16, SpriteSheet.blocks);
	public static Sprite WoodWall = new Sprite(16, 4, 17, SpriteSheet.blocks);
	public static Sprite Dumby = new Sprite(16, 0, 0, SpriteSheet.UndeadCaster_down);
	
	
	
	public static Sprite Cactus = new Sprite(16, 26, 16, SpriteSheet.blocks);
	public static Sprite FlowerCactus = new Sprite(16, 27, 16, SpriteSheet.blocks);
	public static Sprite ColoredFlowers = new Sprite(16, 17, 23, SpriteSheet.blocks);
	public static Sprite YellowFlowers = new Sprite(16, 17, 22, SpriteSheet.blocks);
	public static Sprite Pebble = new Sprite(16, 25, 17, SpriteSheet.blocks);
	public static Sprite BlueMushroom = new Sprite(16, 21, 18, SpriteSheet.blocks);
	public static Sprite BlueMushroomDirt = new Sprite(16, 22, 18, SpriteSheet.blocks);
	public static Sprite RedMushroom = new Sprite(16, 23, 18, SpriteSheet.blocks);
	public static Sprite RedMushroomDirt = new Sprite(16, 24, 18, SpriteSheet.blocks);
	public static Sprite GreenMushroom = new Sprite(16, 25, 18, SpriteSheet.blocks);
	public static Sprite GreenMushroomDirt = new Sprite(16, 26, 18, SpriteSheet.blocks);
	public static Sprite DirtPatch = new Sprite(16, 17, 16, SpriteSheet.blocks);
	public static Sprite DarkCastle = new Sprite(16, 19, 25, SpriteSheet.blocks);
	public static Sprite Cave = new Sprite(16, 19, 24, SpriteSheet.blocks);
	public static Sprite Portal = new Sprite(16, 19, 28, SpriteSheet.blocks);
	public static Sprite PathVertical = new Sprite(16, 17, 20, SpriteSheet.blocks);
	public static Sprite PathHorizontal = new Sprite(16, 18, 20, SpriteSheet.blocks);
	public static Sprite PathCross = new Sprite(16, 17, 21, SpriteSheet.blocks);
	public static Sprite PathCornerTL = new Sprite(16, 17, 18, SpriteSheet.blocks);
	public static Sprite PathCornerTR = new Sprite(16, 18, 18, SpriteSheet.blocks);
	public static Sprite PathCornerBL = new Sprite(16, 17, 19, SpriteSheet.blocks);
	public static Sprite PathCornerBR = new Sprite(16, 18, 19, SpriteSheet.blocks);
	public static Sprite PathEndLeft = new Sprite(16, 18, 24, SpriteSheet.blocks);
	public static Sprite PathEndRight = new Sprite(16, 17, 24, SpriteSheet.blocks);
	public static Sprite PathEndTop = new Sprite(16, 19, 23, SpriteSheet.blocks);
	public static Sprite PathEndBottom = new Sprite(16, 18, 23, SpriteSheet.blocks);
	public static Sprite BrokenSword = new Sprite(16, 18, 16, SpriteSheet.blocks);
	public static Sprite RedBed = new Sprite(16, 16, 17, SpriteSheet.blocks);
	public static Sprite BlueBed = new Sprite(16, 16, 19, SpriteSheet.blocks);
	public static Sprite GreenBed = new Sprite(16, 16, 20, SpriteSheet.blocks);
	public static Sprite OrangeBed = new Sprite(16, 16, 21, SpriteSheet.blocks);
	public static Sprite TopChair = new Sprite(16, 19, 21, SpriteSheet.blocks);
	public static Sprite BottomChair = new Sprite(16, 19, 22, SpriteSheet.blocks);
	public static Sprite LeftChair = new Sprite(16, 18, 22, SpriteSheet.blocks);
	public static Sprite RightChair = new Sprite(16, 18, 21, SpriteSheet.blocks);
	public static Sprite Castle = new Sprite(16, 19, 26, SpriteSheet.blocks);
	public static Sprite Village = new Sprite(16, 19, 27, SpriteSheet.blocks);
	public static Sprite Table = new Sprite(16, 19, 20, SpriteSheet.blocks);
	public static Sprite Door = new Sprite(16, 4, 0, SpriteSheet.blocks);
	public static Sprite Anvil = new Sprite(16, 20, 16, SpriteSheet.blocks);
	public static Sprite Stove = new Sprite(16, 23, 19, SpriteSheet.blocks);



	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
		
	
	
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		if (width == height) {
			SIZE = width * height;
		} else {
			SIZE = 0;
		}
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		load();
		
	
	
	}
	
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet, int random) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		load();
		
	
	
	}
	
	
	 /*  public static Sprite rotate(Sprite sprite) {
		   
		      int width = (int) (sprite.width);
		      int height = (int) (sprite.height);
		      int[] pixels = new int[width * height];
		      for (int y = 0; y < height; y++){
		         for (int x = 0; x < width; x++){
		        		 pixels[x + y * width] = sprite.pixels[(int) y + x * sprite.width];
		        	 }
		      }
		      Sprite rSprite = new Sprite(pixels, width, height);
		      
		      return rSprite;
		   }
	   
	   public static Sprite rotate(Sprite sprite, int degrees) {
		   
		return null;
	   }*/
	   
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int [SIZE*SIZE];
		setColor(color);
	}
	
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		//System.arraycopy(pixels, 0, this.pixels, 0, pixels.length);
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}

	}
	   public Sprite(int width, int height, Color opaque2) {
		   this.SIZE = width * height;
		   this.width = width;
		   this.height = height;
		   pixels = new int[width * height];
		   setColor(opaque2);
	}

	public static Sprite resize(Sprite sprite, double mag){
		      
		      int[] pixels = new int[(int) (sprite.pixels.length * mag * mag)];
		      int width = (int) (sprite.width * mag);
		      int height = (int) (sprite.height * mag);
		      for (int y = 0; y < height;y++){
		         for (int x = 0; x < width;x++){
		            pixels[x+y*width] = sprite.pixels[(int) (x/mag+Math.floor(y/mag)*sprite.width)];
		         }
		      }
		      
		      Sprite nSprite = new Sprite(pixels, width, height);
		      
		      return nSprite;
		   }
	   
	
	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}
	
	
	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		
		double nx_x = rot_x(-angle, 1.0, 0.0);
		double nx_y = rot_y(-angle, 1.0, 0.0);
		double ny_x = rot_x(-angle, 0.0, 1.0);
		double ny_y = rot_y(-angle, 0.0, 1.0);
		
		double x0 = rot_x(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double y0 = rot_y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;
		
		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				
				int col = 0;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) col = 0xffFF3AFB /*0xff00CFCB*/;
				else col = pixels[xx + yy * width];
				result[x + y * width] = col;
				
				x1 += nx_x;
				y1 += nx_y;
				
			}
			
			x0 += ny_x;
			y0 += ny_y;
			
		}

		
		return result;
	}
	
	
	private static double rot_x(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * cos + y * -sin;
	}
	
	private static double rot_y(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * sin + y * cos;
	}
	   
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT;
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {		
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {
				
				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];

						/*System.out.println("SPRITE_WIDTH: " + sheet.SPRITE_WIDTH + " SPRITE_HEIGHT: " + sheet.SPRITE_HEIGHT);
						System.out.println("getWidth: " + sheet.getWidth() + " getHeight: " + sheet.getHeight());
						System.out.println("getPixels: " + sheet.getPixels());*/
						
					} 
				}
				
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
		}
		return sprites;
	}
	
	private void setColor(int color) {
		for (int i = 0; i < height * width; i++) {
			pixels[i] = color;
		}
	}
	
	private void setColor(Color opaque2) {
		for (int i = 0; i < height * width; i++) {
			pixels[i] = opaque2.getRGB();
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
			
			private void load() {
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++)
						pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
				}
			}
			
			{
	}

			public int getX() {
				return x;
			}
			public int getY() {
				return y;
			}

			public void setPosition(int x, int y) {
				this.x = x;
				this.y = y;
			}
}