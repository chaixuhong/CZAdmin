export default {

  methods: {
    /**
     * 通用的提示封装
     */
    submitSuccessNotify () {
      this.$notify({
        title: '提交成功',
        type: 'success',
        duration: 2500
      })
    },
    addSuccessNotify () {
      this.$notify({
        title: '新增成功',
        type: 'success',
        duration: 2500
      })
    },
    editSuccessNotify () {
      this.$notify({
        title: '编辑成功',
        type: 'success',
        duration: 2500
      })
    },
    delSuccessNotify () {
      this.$notify({
        title: '删除成功',
        type: 'success',
        duration: 2500
      })
    }
  }

}
