import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns, ProDescriptionsItemProps } from '@ant-design/pro-components';
import {
  FooterToolbar,
  PageContainer,
  ProDescriptions,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import { Button, Drawer, message } from 'antd';
import React, { useRef, useState } from 'react';
import {
  addInterfaceInfo,
  deleteInterfaceInfo,
  listInterfaceInfoByPage,
  offlineInterfaceInfo,
  onlineInterfaceInfo,
  updateInterfaceInfo,
} from '@/services/multi-api-system/interfaceInfoController';
import { SortOrder } from 'antd/es/table/interface';
import CreateModal from './components/CreateModal';
import UpdateModal from './components/UpdateModal';

const InterfaceInfoTable: React.FC = () => {
  /**
   * @en-US Pop-up window of new window
   * @zh-CN 新建窗口的弹窗
   *  */
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  /**
   * @en-US The pop-up window of the distribution update window
   * @zh-CN 分布更新窗口的弹窗
   * */
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);
  /**
   * 抽屉元素
   */
  const [showDetail, setShowDetail] = useState<boolean>(false);
  /**
   * 表格控制
   */
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.InterfaceInfoDo>();
  const [selectedRowsState, setSelectedRows] = useState<API.InterfaceInfoDo[]>([]);

  /**
   * 添加操作
   * @param fields
   */
  const handleAdd = async (fields: API.InterfaceInfoDo) => {
    const hide = message.loading('正在添加');
    try {
      await addInterfaceInfo({
        ...fields,
      });
      hide();
      message.success('Added successfully');
      handleModalOpen(false);
      return true;
    } catch (error) {
      hide();
      message.error('Adding failed, please try again!');
      return false;
    }
  };

  /**
   * 更新操作
   * @param fields
   */
  const handleUpdate = async (fields: API.InterfaceInfoDo) => {
    const hide = message.loading('Configuring');
    try {
      await updateInterfaceInfo({
        id: currentRow?.id,
        ...fields,
      });
      hide();
      message.success('Configuration is successful');
      handleUpdateModalOpen(false);
      return true;
    } catch (error) {
      hide();
      message.error('Configuration failed, please try again!');
      return false;
    }
  };

  /**
   * 上线
   * @param record
   */
  const handleOnline = async (record: API.InterfaceInfoDo) => {
    const hide = message.loading('发布中');
    if (!record) return true;
    try {
      await onlineInterfaceInfo({
        id: record.id,
      });
      hide();
      message.success('操作成功');
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      message.error('操作失败，' + error.message);
      return false;
    }
  };

  /**
   * 下线
   * @param record
   */
  const handleOffline = async (record: API.InterfaceInfoDo) => {
    const hide = message.loading('下线中');
    if (!record) return true;
    try {
      await offlineInterfaceInfo({
        id: record.id,
      });
      hide();
      message.success('操作成功');
      actionRef.current?.reload();
      return true;
    } catch (error: any) {
      hide();
      message.error('操作失败，' + error.message);
      return false;
    }
  };

  /**
   * 删除操作
   * @param record
   */
  const handleRemove = async (record: API.InterfaceInfoDo) => {
    const hide = message.loading('正在删除');
    if (!record) return true;
    try {
      await deleteInterfaceInfo({
        id: record.id,
      });
      hide();
      message.success('Deleted successfully and will refresh soon');
      actionRef.current?.reload();
      return true;
    } catch (error) {
      hide();
      message.error('Delete failed, please try again');
      return false;
    }
  };

  const columns: ProColumns<API.InterfaceInfoDo>[] = [
    {
      title: '接口名称',
      dataIndex: 'name',
      valueType: 'text',
      formItemProps: {
        rules: [
          {
            required: true,
          },
        ],
      },
    },
    {
      title: '描述',
      dataIndex: 'description',
      valueType: 'textarea',
    },
    {
      title: '请求方法',
      dataIndex: 'method',
      valueType: 'text',
    },
    {
      title: 'url',
      dataIndex: 'url',
      valueType: 'text',
    },
    {
      title: '请求参数',
      dataIndex: 'requestParams',
      valueType: 'jsonCode',
    },
    {
      title: '请求头',
      dataIndex: 'requestHeader',
      valueType: 'jsonCode',
    },
    {
      title: '响应头',
      dataIndex: 'responseHeader',
      valueType: 'jsonCode',
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '关闭',
          status: 'Default',
        },
        1: {
          text: '开启',
          status: 'Processing',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => {
        let operations = [
          <a
            key="config"
            onClick={() => {
              handleUpdateModalOpen(true);
              setCurrentRow(record);
            }}
          >
            修改
          </a>,
        ];
        if (record.status === 0){
          operations.push(<a
            key="config"
            onClick={() => {
              handleOnline(record);
            }}
          >
            发布
          </a>);
        }
        if (record.status === 1){
          operations.push(<a
            key="config"
            onClick={() => {
              handleOffline(record);
            }}
          >
            下线
          </a>);
        }
        operations.push(<a
          type="text"
          key="config"
          onClick={() => {
            handleRemove(record);
          }}
        >
          删除
        </a>);
        return operations;
      },
    },
  ];
  return (
    <PageContainer>
      <ProTable<API.InterfaceInfoDo, API.PageParams>
        headerTitle={'查询表格'}
        actionRef={actionRef}
        rowKey="key"
        search={{
          labelWidth: 120,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalOpen(true);
            }}
          >
            <PlusOutlined /> 新建
          </Button>,
        ]}
        request={async (
          params,
          sort: Record<string, SortOrder>,
          filter: Record<string, (string | number)[] | null>,
        ) => {
          const res: any = await listInterfaceInfoByPage({
            ...params,
          });
          if (res?.code === 23200) {
            return {
              data: res?.data.records || [],
              success: true,
              total: res?.data.total || 0,
            };
          } else {
            return {
              data: [],
              success: false,
              total: 0,
            };
          }
        }}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
      />
      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              已选择{' '}
              <a
                style={{
                  fontWeight: 600,
                }}
              >
                {selectedRowsState.length}
              </a>{' '}
              项 &nbsp;&nbsp;
              <span>
                服务调用次数总计 {selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)} 万
              </span>
            </div>
          }
        ></FooterToolbar>
      )}
      <UpdateModal
        columns={columns}
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalOpen(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalOpen(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        open={updateModalOpen}
        values={currentRow || {}}
      />
      <CreateModal
        columns={columns}
        onCancel={() => {
          handleModalOpen(false);
        }}
        onSubmit={async (values) => {
          let success = await handleAdd(values);
          if (success && actionRef.current) {
            actionRef.current.reload();
          }
        }}
        open={createModalOpen}
      />
      <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<API.InterfaceInfoDo>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<API.InterfaceInfoDo>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};
export default InterfaceInfoTable;
