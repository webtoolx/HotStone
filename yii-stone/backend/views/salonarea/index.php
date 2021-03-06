<?php

/* @var $this yii\web\View */
/* @var $searchModel backend\models\SalonareaSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

use yii\helpers\Html;
use kartik\export\ExportMenu;
use kartik\grid\GridView;

$this->title = Yii::t('stone', 'Salonareas');
$this->params['breadcrumbs'][] = $this->title;
$search = "$('.search-button').click(function(){
	$('.search-form').toggle(1000);
	return false;
});";
$this->registerJs($search);
?>
<div class="salonarea-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a(Yii::t('stone', 'Create Salonarea'), ['create'], ['class' => 'btn btn-success']) ?>
        <?= Html::a(Yii::t('stone', 'Advance Search'), '#', ['class' => 'btn btn-info search-button']) ?>
    </p>
    <div class="search-form" style="display:none">
        <?=  $this->render('_search', ['model' => $searchModel]); ?>
    </div>
    <?php 
    $gridColumn = [
        ['class' => 'yii\grid\SerialColumn'],
        ['attribute' => 'id', 'visible' => false],
        'hair_sweep_and_mop_floors',
        'clean_under_chairs_from_hair_and_put_leader_conditionner_for_cha',
        'hair_remove_stains_and_dust_on_surfaces',
        'hair_clean_mirrors',
        'hair_clean_cart_and_trolleys',
        'hair_clean_drawers_and_cabinets',
        'hair_empty_trash',
        'hair_detect_and_change_busted_light',
        'shampoo_sweep_and_mop_floors',
        'shampoo_clean_under_chairs_from_hair_and_put_leader',
        'shampoo_clean_sinks_from_hair_and_product_residue',
        'shampoo_clean_shampoo_bowls',
        'shampoo_empty_trash',
        'shampoo_remove_stains_and_dust_on_surfaces',
        'shampoo_clean_mirrors',
        'shampoo_clean_the_product_containers',
        'shampoo_empty_towel_basket',
        'shampoo_detect_and_change_busted_light',
        'treatment_sweep_and_mop_floors',
        'treatment_clean_under_chairs_from_hair_and_put_leader',
        'treatment_clean_sinks_from_hair_and_product_residue',
        'treatment_empty_trash',
        'treatment_remove_stains_and_dust_on_surfaces',
        'treatment_draw_up_the_decoration',
        'treatment_clean_mixed_bowls',
        'treatment_clean_mirrors',
        'treatment_clean_the_product_containers',
        'treatment_empty_used_towel_bascket',
        'treatment_clean_the_product_containers2',
        'treatment_detect_and_change_busted_light',
        'pedicure_the_curtains_should_be_clean',
        'pedicure_sweep_and_mop_floors',
        'pedicure_clean_under_chairs__and_put_leader',
        'pedicure_clean_and_sanitize_sink',
        'pedicure_empty_trash',
        'pedicure_remove_stains_and_dust_on_surfaces',
        'pedicure_remove_basket_cleaning_items_and_dirty_towels',
        'pedicure_remove_empty_glasses',
        'manicure_bar_draw_up_products',
        'manicure_bar_empty_trash',
        'manicure_bar_clean_all_surfaces_with_a_disinfectant',
        'manicure_bar_clean_and_organize_nail_polish_racks',
        'manicure_bar_empty_used_towel_bascket',
        'shift_am',
        'shift_pm',
        'shift_date',
        [
                'attribute' => 'checked_by',
                'label' => Yii::t('stone', 'Checked By'),
                'value' => function($model){                   
                    return $model->checkedBy->username;                   
                },
                'filterType' => GridView::FILTER_SELECT2,
                'filter' => \yii\helpers\ArrayHelper::map(\common\models\User::find()->asArray()->all(), 'id', 'username'),
                'filterWidgetOptions' => [
                    'pluginOptions' => ['allowClear' => true],
                ],
                'filterInputOptions' => ['placeholder' => 'User', 'id' => 'grid-salonarea-search-checked_by']
            ],
        ['attribute' => 'lock', 'visible' => false],
        [
            'class' => 'yii\grid\ActionColumn',
            'template' => '{save-as-new} {view} {update} {delete}',
            'buttons' => [
                'save-as-new' => function ($url) {
                    return Html::a('<span class="glyphicon glyphicon-copy"></span>', $url, ['title' => 'Save As New']);
                },
            ],
        ],
    ]; 
    ?>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => $gridColumn,
        'pjax' => true,
        'pjaxSettings' => ['options' => ['id' => 'kv-pjax-container-salonarea']],
        'panel' => [
            'type' => GridView::TYPE_PRIMARY,
            'heading' => '<span class="glyphicon glyphicon-book"></span>  ' . Html::encode($this->title),
        ],
        // your toolbar can include the additional full export menu
        'toolbar' => [
            '{export}',
            ExportMenu::widget([
                'dataProvider' => $dataProvider,
                'columns' => $gridColumn,
                'target' => ExportMenu::TARGET_BLANK,
                'fontAwesome' => true,
                'dropdownOptions' => [
                    'label' => 'Full',
                    'class' => 'btn btn-default',
                    'itemsBefore' => [
                        '<li class="dropdown-header">Export All Data</li>',
                    ],
                ],
            ]) ,
        ],
    ]); ?>

</div>
