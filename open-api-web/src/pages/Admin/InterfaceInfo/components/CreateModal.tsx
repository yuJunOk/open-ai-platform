import type {ProColumns, ProFormInstance} from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import '@umijs/max';
import { Modal } from 'antd';
import React from 'react';

export type Props = {
  columns: ProColumns<API.InterfaceInfoDo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfoDo) => Promise<void>;
  open: boolean;
};

const CreateModal: React.FC<Props> = (props) => {
  const { open, columns, onCancel, onSubmit } = props;

  return (
    <Modal open={open} footer={null} onCancel={() => onCancel?.()}>
      <ProTable
        type="form"
        columns={columns}
        onSubmit={async (value) => {
          onSubmit?.(value);
        }}
      />
    </Modal>
  );
};
export default CreateModal;
